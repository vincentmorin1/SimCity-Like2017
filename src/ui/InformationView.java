package ui;

import java.util.Observable;
import java.util.Observer;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.GameBoard;
import model.tools.InformationTool;

public class InformationView extends JPanel implements Observer {

    private static final long serialVersionUID = 1L;

    /**
     * There are maximum 5 fields of information per building.
     */
    private JLabel name;
    private JLabel info1;
    private JLabel info2;
    private JLabel info3;
    private JLabel info4;

    /**
     * PropertiesView constructor
     * 
     * @param w
     * @param texts
     */
    public InformationView(GameBoard w, InformationTool it) {
        super();
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        String[] info = it.getInformations();
        int n = info.length;
        
        this.name = new JLabel(info[0]);
        this.add(this.name);
        
        if (n>1){this.info1 = new JLabel(info[1]); } else { this.info1 = new JLabel(""); }
        if (n>2){this.info2 = new JLabel(info[2]); } else { this.info2 = new JLabel(""); }
        if (n>3){this.info3 = new JLabel(info[3]); } else { this.info3 = new JLabel(""); }
        if (n>4){this.info4 = new JLabel(info[4]); } else { this.info4 = new JLabel(""); }
        
        this.add(this.info1);
        this.add(this.info2);
        this.add(this.info3);
        this.add(this.info4);
    }


    @Override
    /**
     * Update the detailed information view.
     */
    public void update(Observable o, Object arg) {
        assert o instanceof GameBoard;
        GameBoard world = (GameBoard) o;

        String[] info = world.getInformationTool().getInformations();
        int n = info.length;

        this.name.setText(info[0]);
       
        if (n>1){this.info1.setText(info[1]); } else { this.info1.setText(""); }
        if (n>2){this.info2.setText(info[2]); } else { this.info2.setText(""); }
        if (n>3){this.info3.setText(info[3]); } else { this.info3.setText(""); }
        if (n>4){this.info4.setText(info[4]); } else { this.info4.setText(""); }
    }

}
