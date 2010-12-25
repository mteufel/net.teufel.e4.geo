package net.teufel.e4.geo;

import java.util.List;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

public class CollectionContentProvider implements IStructuredContentProvider {

	public Object[] getElements(Object inputElement) {
		return ((List<?>) inputElement).toArray();
	}

	public void dispose() {
	}

	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
	}

}
