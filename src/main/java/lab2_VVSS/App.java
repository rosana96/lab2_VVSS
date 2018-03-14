package lab2_VVSS;


import lab2_VVSS.controller.ClientController;
import lab2_VVSS.repository.DataManager;
import lab2_VVSS.ui.ElectricaUI;

public class App {
	public static void main(String[] args) {
		DataManager repo = new DataManager();
		
		ClientController ctrl = new ClientController(repo);
		
		ElectricaUI console = new ElectricaUI(ctrl);
		console.Run();
	}
}
