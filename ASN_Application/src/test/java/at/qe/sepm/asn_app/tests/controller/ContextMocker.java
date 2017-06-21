package at.qe.sepm.asn_app.tests.controller;

import javax.faces.context.FacesContext;

import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.primefaces.context.RequestContext;

/**
 * Created by Bernd Menia <bernd.menia@student.uibk.ac.at>
 * on 12.06.17 11:01 CEST.
 */
public abstract class ContextMocker extends FacesContext {

    private static final Release RELEASE = new Release();


    private ContextMocker() {
    }


    private static class Release implements Answer<Void> {
        @Override
        public Void answer(InvocationOnMock invocation) throws Throwable {
            setCurrentInstance(null);
            return null;
        }
    }


    public static FacesContext mockFacesContext() {
        FacesContext context = Mockito.mock(FacesContext.class);
        setCurrentInstance(context);
        Mockito.doAnswer(RELEASE)
                .when(context)
                .release();
        return context;
    }


    public static RequestContext mockRequestContext() {
        RequestContext requestContext = Mockito.mock(RequestContext.class);
        RequestContext.setCurrentInstance(requestContext, mockFacesContext());
        return requestContext;
    }
}