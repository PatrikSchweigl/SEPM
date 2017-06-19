package at.qe.sepm.c4f_app.configs;


import java.io.IOException;

import javax.faces.application.ResourceDependencies;
import javax.faces.application.ResourceDependency;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.render.Renderer;

@ResourceDependencies({
    @ResourceDependency(library="primefaces-le-frog", name="theme.css"),
})
public class HeadRendererLeFrogTheme extends Renderer {

    @Override
    public void encodeBegin(FacesContext context, UIComponent component) throws IOException {
        context.getResponseWriter().startElement("head", component);
    }

    @Override
    public void encodeChildren(FacesContext context, UIComponent component) throws IOException {
        // NOOP.
    }

    @Override
    public void encodeEnd(FacesContext context, UIComponent component) throws IOException {
        for (UIComponent resource : context.getViewRoot().getComponentResources(context, "head")) {
            resource.encodeAll(context);
        }

        context.getResponseWriter().endElement("head");
    }

}