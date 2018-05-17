package photoshop;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class SliderOptionBox {
	
	public static boolean isValuePicked = false;
	public static int chosenValue = 0;
	
  public static void main(final String[] args) {
    JFrame parent = new JFrame();

    JOptionPane optionPane = new JOptionPane();
    JSlider slider = getSlider(optionPane);
    optionPane.setMessage(new Object[] { "Value is: ", slider });
    optionPane.setMessageType(JOptionPane.QUESTION_MESSAGE);
    optionPane.setOptionType(JOptionPane.OK_CANCEL_OPTION);
    JDialog dialog = optionPane.createDialog(parent, "My Slider");
    dialog.setVisible(true);
    System.out.println("Input: " + optionPane.getInputValue());
    isValuePicked = true;
    chosenValue = (int)optionPane.getInputValue();
  }

  static JSlider getSlider(final JOptionPane optionPane) {
    JSlider slider = new JSlider();
    slider.setMaximum(255);
    slider.setMinimum(0);
    slider.setMajorTickSpacing(50);
    slider.setPaintTicks(true);
    slider.setPaintLabels(true);
    ChangeListener changeListener = new ChangeListener() {
      public void stateChanged(ChangeEvent changeEvent) {
        JSlider theSlider = (JSlider) changeEvent.getSource();
        if (!theSlider.getValueIsAdjusting()) {
          optionPane.setInputValue(new Integer(theSlider.getValue()));
          optionPane.setMessage(new Object[] {"Value is: " + optionPane.getInputValue(), slider});
        }
      }
    };
    slider.addChangeListener(changeListener);
    return slider;
  }

}
