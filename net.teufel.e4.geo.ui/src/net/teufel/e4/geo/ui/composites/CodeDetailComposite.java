package net.teufel.e4.geo.ui.composites;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import net.teufel.e4.geo.domain.Code;
import net.teufel.e4.geo.util.ProxyAuthenticator;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.ui.di.Focus;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.layout.GridData;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.ui.forms.widgets.Form;
import org.eclipse.swt.layout.FillLayout;

@SuppressWarnings("restriction")
public class CodeDetailComposite extends Composite {

	private Code myCode;
	@Inject
	private MPart part;
	private final FormToolkit toolkit = new FormToolkit(Display.getCurrent());
	private Form form;
	private Text textPostleitzahl;
	private Text textName;
	private Text textLand;
	private Text textBundesland1;
	private Text textBundesland2;
	private Text textGemeindeschluessel;
	private Text textRegierungsbezirk;
	private Text textLandkreis;
	private Label labelLandIcon;
	private Composite compositeKarte;
	private Section sectionKarte;
	private Label labelKarte;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	@SuppressWarnings("unused")
	@Inject
	public CodeDetailComposite(Composite parent, @Optional @Named(IServiceConstants.ACTIVE_SELECTION) Code code) {
		super(parent, SWT.NONE);
		this.myCode = code;
		addDisposeListener(new DisposeListener() {
			public void widgetDisposed(DisposeEvent e) {
				toolkit.dispose();
			}
		});
		
		toolkit.adapt(this);
		toolkit.paintBordersFor(this);
		setLayout(new FillLayout(SWT.HORIZONTAL));
		
		form = toolkit.createForm(this);
		toolkit.paintBordersFor(form);
		form.setText("");
		toolkit.decorateFormHeading(form);
		form.getBody().setLayout(new GridLayout(2, false));
		
		Section sectionAllgmein = toolkit.createSection(form.getBody(), Section.TWISTIE | Section.TITLE_BAR);
		sectionAllgmein.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
		toolkit.paintBordersFor(sectionAllgmein);
		sectionAllgmein.setText("Allgemein");
		sectionAllgmein.setExpanded(true);
		
		Composite compositeAllgemein = toolkit.createComposite(sectionAllgmein, SWT.NONE);
		toolkit.paintBordersFor(compositeAllgemein);
		sectionAllgmein.setClient(compositeAllgemein);
		GridLayout gl_compositeAllgemein = new GridLayout(3, false);
		compositeAllgemein.setLayout(gl_compositeAllgemein);
		
		Label labelPostalcode = toolkit.createLabel(compositeAllgemein, "Postleitzahl:", SWT.NONE);
		
		textPostleitzahl = toolkit.createText(compositeAllgemein, "New Text", SWT.NONE);
		textPostleitzahl.setText("");
		textPostleitzahl.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));
		
		Label labelName = toolkit.createLabel(compositeAllgemein, "Name:", SWT.NONE);
		
		textName = toolkit.createText(compositeAllgemein, "New Text", SWT.NONE);
		textName.setText("");
		textName.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));
		
		Label labelCountryCode = toolkit.createLabel(compositeAllgemein, "Land:", SWT.NONE);
		
		labelLandIcon = toolkit.createLabel(compositeAllgemein, " ", SWT.NONE);
		GridData gd_labelLandIcon = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_labelLandIcon.minimumWidth = 8;
		gd_labelLandIcon.widthHint = 16;
		labelLandIcon.setLayoutData(gd_labelLandIcon);
		
		textLand = toolkit.createText(compositeAllgemein, "New Text", SWT.NONE);
		textLand.setText("");
		textLand.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Section sectionAdministration = toolkit.createSection(form.getBody(), Section.TWISTIE | Section.TITLE_BAR);
		sectionAdministration.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
		toolkit.paintBordersFor(sectionAdministration);
		sectionAdministration.setText("Administration");
		sectionAdministration.setExpanded(true);
		
		Composite compositeAdministration = toolkit.createComposite(sectionAdministration, SWT.NONE);
		toolkit.paintBordersFor(compositeAdministration);
		sectionAdministration.setClient(compositeAdministration);
		compositeAdministration.setLayout(new GridLayout(2, false));
		
		Label labelBundesland1 = toolkit.createLabel(compositeAdministration, "Bundesland 1:", SWT.NONE);
		
		textBundesland1 = toolkit.createText(compositeAdministration, "New Text", SWT.NONE);
		textBundesland1.setText("");
		textBundesland1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label labelBundesland2 = toolkit.createLabel(compositeAdministration, "Bundesland 2:", SWT.NONE);
		
		textBundesland2 = toolkit.createText(compositeAdministration, "New Text", SWT.NONE);
		textBundesland2.setText("");
		textBundesland2.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label labelGemeindeschluessel = toolkit.createLabel(compositeAdministration, "Gemeindeschl\u00FCssel:", SWT.NONE);
		
		textGemeindeschluessel = toolkit.createText(compositeAdministration, "New Text", SWT.NONE);
		textGemeindeschluessel.setText("");
		textGemeindeschluessel.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label labelRegierungsbezirk = toolkit.createLabel(compositeAdministration, "Regierungsbezirk:", SWT.NONE);
		
		textRegierungsbezirk = toolkit.createText(compositeAdministration, "New Text", SWT.NONE);
		textRegierungsbezirk.setText("");
		textRegierungsbezirk.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label labelLandkreis = toolkit.createLabel(compositeAdministration, "Landkreis:", SWT.NONE);
		
		textLandkreis = toolkit.createText(compositeAdministration, "New Text", SWT.NONE);
		textLandkreis.setText("");
		textLandkreis.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		sectionKarte = toolkit.createSection(form.getBody(), Section.TWISTIE | Section.TITLE_BAR);
		sectionKarte.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, true, 2, 1));
		toolkit.paintBordersFor(sectionKarte);
		sectionKarte.setText("Karte");
		sectionKarte.setExpanded(true);
		
		compositeKarte = toolkit.createComposite(sectionKarte, SWT.NONE);
		toolkit.paintBordersFor(compositeKarte);
		sectionKarte.setClient(compositeKarte);
		compositeKarte.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		labelKarte = toolkit.createLabel(compositeKarte, "", SWT.NONE);
//		browser.setBackground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		

	}
	
	@PostConstruct
	private void initUi() {
		if (this.myCode != null) {
			form.setText(myCode.getPlz()  + " " + myCode.getOrt() + " (" + myCode.getCountryCode() + ")");
			part.setLabel(myCode.getPlz());
			textPostleitzahl.setText(myCode.getPlz());
			textName.setText(myCode.getOrt());
			
			URL url;
			try {
				url = FileLocator.resolve(new URL("platform:/plugin/net.teufel.e4.geo.ui/icons/flags/" + myCode.getCountryCode().toLowerCase() + ".gif"));
				labelLandIcon.setImage(ImageDescriptor.createFromURL(url).createImage());
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			
			textLand.setText(myCode.getCountryCode());
			textBundesland1.setText(myCode.getAdminCode1());
			textBundesland2.setText(myCode.getAdminName1());
			textGemeindeschluessel.setText(myCode.getAdminCode3());
			textRegierungsbezirk.setText(myCode.getAdminName2());
			textLandkreis.setText(myCode.getAdminName3());
			//browser.setUrl(urlX);
			
			
			try {
				ProxyAuthenticator.authenticate();
				String myUrl = myCode.getGoogleMapsUrl(600,800);
				System.out.println(myUrl);
				URL urly = new URL(myUrl);
				InputStream is = urly.openStream();
				Image image = new Image(Display.getCurrent(), is);
				labelKarte.setImage(image);
				is.close();
			} catch (Exception e) {
				e.printStackTrace();
			} 
			
			
		}
	}

	@Focus
	public void doFocus() {
		textPostleitzahl.setFocus();
	}
}