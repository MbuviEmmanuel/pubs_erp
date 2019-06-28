package bootstrapfx;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.*;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.format.DateTimeFormatter;

import static java.awt.Desktop.getDesktop;

/**
 * @author  Wasula Benjamin
 */
public class BootstrapFX {
    private static Validation validation= new Validation();

    /**
     * A generic method to validate if the textual content of an InputControl (TextField(s), PasswordField(s), and
     * ComboBox(es)) is not empty.
     *
     * @param   f
     *          A generic TextInputControl argument. Could either be a TextField, PasswordField or a ComboBox.
     *
     * @return  <tt>true</tt> for every TextInputControl if, and only if, the control argument passed to it is not
     *          empty, otherwise returns false.
     */
    @SafeVarargs
    protected static <Field extends Control> boolean isValid(Field... f) {
        boolean validate= true;

        for (Field field: f)
            if (field instanceof TextField) {
                if (((TextField)field).getText().isEmpty()) {
                    validate= false;
                    setInvalid(field, null);
                } else if (field.getStyleClass().contains("password")) {
                    if (!validation.isPassword(((TextField) field).getText())) {
                        validate= false;
                        setInvalid(
                            field, "Passwords must contain at least: one lowercase letter, one uppercase" +
                            "letter, one digit, one special character and a length of 6 to 16 letters."
                        );
                    }
                } else if (field.getStyleClass().contains("email")) {
                    if (!validation.isEmail(((TextField) field).getText())) {
                        validate= false;
                        setInvalid(field, "Invalid Email Address");
                    }
                }
            } else if (field instanceof ComboBox) {
                if (((ComboBox)field).getValue()==null) {
                    validate= false;
                    setInvalid(field, null);
                }
            }

        return validate;
    }

    /**
     * This method is triggered when a field contains invalid input. Any KeyTypeEvent on this node resets the field back
     * to its normal state
     *
     * Its also customizes the duration for which it takes to display an error tooltip of the provided meassage.
     *
     * @param   field
     *          A generic TextInputControl argument. Could either be a TextField, PasswordField or a ComboBox.
     *
     * @param   message
     *          Custom message to display on the tooltip for an invalid input.
     *          <tt>null</tt> is reset to "required field"
     */
    protected static <Field extends Control> void setInvalid(Field field, String message) {
        try {
            field.setTooltip(new Tooltip((message==null)? "Required field" : message));

            java.lang.reflect.Field fieldBehavior= field.getTooltip().getClass().getDeclaredField("BEHAVIOR");
            fieldBehavior.setAccessible(true);
            Object objBehavior= fieldBehavior.get(field.getTooltip());

            java.lang.reflect.Field fieldTimer= objBehavior.getClass().getDeclaredField("activationTimer");
            fieldTimer.setAccessible(true);
            Timeline objTimer= (Timeline) fieldTimer.get(objBehavior);

            objTimer.getKeyFrames().clear();
            objTimer.getKeyFrames().add(new KeyFrame(new Duration(0)));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            field.getStyleClass().add("field-error");
            field.setOnKeyTyped(event -> {
                field.getStyleClass().removeAll("field-error");
                field.setTooltip(null);
            });
        }
    }

    /**
     * A generic method that gets the textual content of TextInputControl(s) arguments passed to it as variable
     * arguments and returns an array containing textual content of all the InputControl arguments passed to it.
     * This encompasses TextField(s), PasswordField(s), ComboBox(es) and DatePicker(s).
     *
     * @param   f
     *          A generic control. Could either be a TextField, PasswordField, DatePicker or a ComboBox.
     *
     * @return  An array containing textual content of all the InputControl arguments passed to it.
     */
    @SafeVarargs
    protected static <Field extends Control> String[] getText(Field... f) {
        final String[] text= new String[f.length];

        for (int i= 0; i<f.length; i++)
            if (f[i] instanceof TextField)
                text[i]= ((TextField)f[i]).getText();
            else if (f[i] instanceof DatePicker)
                text[i]= ((DatePicker)f[i]).getValue().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
            else if (f[i] instanceof ComboBox)
                text[i]= ((ComboBox)f[i]).getValue().toString();

        return text;
    }

    /**
     * A generic method that clears the textual content of all TextInputControl(s) arguments passed to it. This
     * encompasses TextField(s), PasswordField(s), ComboBox(es) and DatePicker(s).
     *
     * @param   <Field>
     *          Type bounded parameter
     *          f
     *          A generic control. Could either be a TextField, PasswordField, DatePicker or a ComboBox.
     */
    @SafeVarargs
    protected static <Field extends Control> void clear(Field... f) {
        for (Field field: f)
            if (field instanceof ComboBox)
                ((ComboBox)field).getSelectionModel().clearSelection();
            else if (field instanceof TextField)
                ((TextField)field).clear();
            else if (field instanceof DatePicker)
                ((DatePicker)field).setValue(null);
    }

    /*
     * Customized method to open hypelinks in the default desktop browser.
     *
     * @param uri   Expected URI token open in browser
     */
    protected static void openHyperlink(String uri) {
        try {
            getDesktop().browse(new URI(uri));
        } catch (IOException | URISyntaxException e) {
            System.err.println(e.getClass().getName()+": "+e.getMessage());
        }
    }
}