package net.teufel.e4.geo.ui.composites;

import java.net.URL;
import java.util.List;

import javax.inject.Inject;


import net.teufel.e4.geo.CollectionContentProvider;
import net.teufel.e4.geo.domain.Code;
import net.teufel.e4.geo.interfaces.IGeonameService;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.e4.core.di.extensions.Preference;
import org.eclipse.e4.ui.workbench.modeling.EPartService;
import org.eclipse.e4.ui.workbench.modeling.EPartService.PartState;
import org.eclipse.e4.ui.workbench.modeling.ESelectionService;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;

@SuppressWarnings("restriction")
public class SucheComposite extends Composite {

	@Inject
	@Preference(value="actualCountryCode")
	private String actualCountryCode;
	
	private Text text;
	
	@Inject
	private ESelectionService selectionService;
	
	@Inject
    private EPartService partService;
	
	@Inject 
	private IGeonameService myGeonameService;
	private Table table;

	private TableViewer tableViewer;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	@Inject
	public SucheComposite(Composite parent) {
		super(parent, SWT.NONE);
		setLayout(new GridLayout(1, false));
		
		text = new Text(this, SWT.BORDER);
		text.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				sucheAusfuehren(text.getText());
				text.selectAll();
			};
		});
		text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		tableViewer = new TableViewer(this, SWT.BORDER | SWT.FULL_SELECTION);
		table = tableViewer.getTable();
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

		TableViewerColumn tableViewerColumnLand = new TableViewerColumn(tableViewer, SWT.NONE);
		TableColumn tableColumnLand = tableViewerColumnLand.getColumn();
		tableColumnLand.setWidth(45);
		tableColumnLand.setText("Land");

		TableViewerColumn tableViewerColumnPLZ = new TableViewerColumn(tableViewer, SWT.NONE);
		TableColumn tableColumnPLZ = tableViewerColumnPLZ.getColumn();
		tableColumnPLZ.setWidth(55);
		tableColumnPLZ.setText("PLZ");
		
		TableViewerColumn tableViewerColumnOrt = new TableViewerColumn(tableViewer, SWT.NONE);
		TableColumn tableColumnOrt = tableViewerColumnOrt.getColumn();
		tableColumnOrt.setWidth(107);
		tableColumnOrt.setText("Ort");
		
		
		tableViewer.setLabelProvider(new PlzResultLabelProvider());
		tableViewer.setContentProvider(new CollectionContentProvider());
		tableViewer.setInput(null);
		
		tableViewer.addSelectionChangedListener(new ISelectionChangedListener() {
			public void selectionChanged(SelectionChangedEvent event) {
		        IStructuredSelection selection = (IStructuredSelection) event.getSelection();
		        selectionService.setSelection(selection.getFirstElement());
		    }
		  });

		tableViewer.addDoubleClickListener(new IDoubleClickListener() {
			
			@Override
			public void doubleClick(DoubleClickEvent event) {
				partService.showPart(partService.createPart("codeId"), PartState.ACTIVATE);
			}
		});

		
	}

	public void sucheAusfuehren(String suchen) {
		
		List<Code> result;
		if (this.actualCountryCode == null || this.actualCountryCode.equals("") ) {
			result = myGeonameService.getCodeByName(suchen);
		} else {
			result = myGeonameService.getCodeByNameAndCountry(suchen, this.actualCountryCode);
		}
		
		tableViewer.setInput(result);
	}
	
	
	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
	
	private class PlzResultLabelProvider implements ITableLabelProvider {
		
		public Image getColumnImage(Object element, int columnIndex) {
				if (columnIndex != 0)
					return null;
				URL url;
				Code code = (Code) element;
				try {
					url = FileLocator.resolve(new URL("platform:/plugin/net.teufel.e4.geo.ui/icons/flags/" + code.getCountryCode().toLowerCase() + ".gif"));
					return ImageDescriptor.createFromURL(url).createImage();
				} catch (Exception e) {
					return null;
				}
		}

		public String getColumnText(Object element, int columnIndex) {
			
			Code plzResult = (Code) element;
			
			switch (columnIndex) {
			case 0:
				return plzResult.getCountryCode();
			case 1:
				return plzResult.getPlz();
			case 2:
				return plzResult.getOrt();
			}
			return "???";
			
		}

		public void addListener(ILabelProviderListener listener) {
		}

		public void dispose() {
		}

		public boolean isLabelProperty(Object element, String property) {
			return false;
		}

		public void removeListener(ILabelProviderListener listener) {
		}
	}

}
