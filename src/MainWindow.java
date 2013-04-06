import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;


/**
 *
 * @author hp
 */
public class MainWindow {

	/**
     *
     */
    protected Shell shell;
	/**
	 * @wbp.nonvisual location=443,257
	 */
//	private final Image image = new Image((Device) null, (String) null);

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			MainWindow window = new MainWindow();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(680, 491);
		shell.setText("SWT Application");
		shell.setLayout(new FillLayout(SWT.VERTICAL));
		
		Label lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setImage(SWTResourceManager.getImage("/import/ahusson/ENS/java_project/ens2012-pj/src/0795fb4877.jpg"));
		lblNewLabel.setText("New Lhahahaabel");

	}
}
