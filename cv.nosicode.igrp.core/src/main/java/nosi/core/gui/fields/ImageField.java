package nosi.core.gui.fields;

/**
 * @author: Emanuel Pereira
 *
 * Apr 13, 2017
 *
 * Description: class to configure image field
 */
public class ImageField extends AbstractField {

    public ImageField(Object model, String name) {
        super();
        this.setName(name);
        this.setTagName(name);
        this.propertie.put("type", "img");
        this.propertie.put("name", "p_" + name);
        this.propertie.put("img", "http://www.slidesjs.com/examples/callbacks/img/example-slide-1.jpg");
        this.propertie.put("placeholder", "");
        this.propertie.put("right", false);
        this.propertie.put("maxlength", 30);
        this.configValue(model);
    }

}
