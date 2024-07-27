
public class Main {
    
    Controller controller;
    View view;
    Model model;


    public Main(){

        view = new View();
        model = new Model();
        controller = new Controller();
        
        
        model.initialise(8, 8, view, controller);
        controller.initialise(model, view);
        view.initialise(model, controller);

        controller.startup();
    }


    public static void main(String[] args){
        new Main();
        
    }
}
