package net.teufel.e4.geo.ui;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import net.teufel.e4.geo.domain.Country;
import net.teufel.e4.geo.interfaces.IGeonameService;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.e4.core.di.extensions.Preference;
import org.eclipse.e4.ui.model.application.ui.menu.impl.ToolItemImpl;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.osgi.service.prefs.BackingStoreException;

@SuppressWarnings("restriction")
public class CountryComboViewerToolItem extends ToolItemImpl {

	@Inject
	@Preference(value="actualCountryCode")
	String actualCountryCode;
	
	private ComboViewer countryComboViewer;

	@Inject
	private IGeonameService geonameService;

	private final Composite parent;
	
	@Inject
	public CountryComboViewerToolItem(Composite parent) {
		
		this.parent = parent;
		countryComboViewer = new ComboViewer(parent, SWT.READ_ONLY);
		countryComboViewer.addSelectionChangedListener(new ISelectionChangedListener() {
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				Country selectedCountry = (Country) ((StructuredSelection) event.getSelection()).getFirstElement();
				setActualCountryCode(selectedCountry);
			}
		});
		countryComboViewer.setContentProvider(ArrayContentProvider.getInstance());
		countryComboViewer.setLabelProvider(new LabelProvider() {
		    @Override
		    public String getText(Object element) {
		    	Country country = (Country) element;
		    	return country.toString();
		    }

		});
		setWidget(countryComboViewer);
	}
	

	@PostConstruct
	private void init() {
		Country germany = geonameService.getCountry("DE");
		Country alle = new Country("ALLE");
		
		List<Country> myCountries = new ArrayList<Country>();
		myCountries.add(alle);
		myCountries.add(germany);
		myCountries.addAll(geonameService.getNeighbours(germany));

		countryComboViewer.setInput(myCountries);
		
		
		if (this.actualCountryCode == null || this.actualCountryCode.equals("")) {
			countryComboViewer.setSelection(new StructuredSelection(alle));
		} else {
			Country selectedCountry = geonameService.getCountry(this.actualCountryCode);
			countryComboViewer.setSelection(new StructuredSelection(selectedCountry));
		}

		

	}
	
	protected void setActualCountryCode(Country country) {
		if (country.getCountryCode() == null) {
			this.actualCountryCode = "";
		} else {
			this.actualCountryCode = country.getCountryCode();			
		}
		IEclipsePreferences prefs = new InstanceScope().getNode("net.teufel.e4.geo.ui");
		prefs.put("actualCountryCode", this.actualCountryCode);
		try {
			prefs.flush();
		} catch (BackingStoreException e) {
			ErrorDialog.openError(parent.getShell(), "Error",
					"Error while storing preferences",
					new Status(IStatus.ERROR, "net.teufel.e4.geo.ui", e.getMessage(),e)
			);
		}
	}
	
}
