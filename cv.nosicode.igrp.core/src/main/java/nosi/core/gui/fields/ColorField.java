package nosi.core.gui.fields;

/**
 * @author: Emanuel Pereira
 *
 * Apr 13, 2017
 *
 * Description: class to configure color field
 */
public class ColorField extends TextField {

    public ColorField(Object model, String name) {
        super(model, name);
        this.propertie.put("type", "color");
        this.propertie.put("format", "hex");
    }

}
