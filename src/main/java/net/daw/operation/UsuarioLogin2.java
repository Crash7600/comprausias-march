package net.daw.operation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.daw.bean.UsuarioBean;
import net.daw.dao.UsuarioDao;
import net.daw.helper.Contexto;
import net.daw.parameter.UsuarioParam;

public class UsuarioLogin2 extends Operation {

    @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Contexto oContexto = (Contexto) request.getAttribute("contexto");
        oContexto.setVista("jsp/mensaje.jsp"); 
        String result;
        UsuarioBean oUsuario = new UsuarioBean();
        UsuarioParam oUsuarioParam=new UsuarioParam(request);       
        oUsuario=oUsuarioParam.load(oUsuario);       
        UsuarioDao oUsuarioDao = new UsuarioDao(oContexto.getEnumTipoConexion());
        oUsuario = oUsuarioDao.get(oUsuario);
        if (oUsuario.getId() != 0) {
            result = "Bienvenido/a " + oUsuario.getNombre() + " Has entrado en la aplicación. Ahora puedes operar con los menús.";
            request.getSession().setAttribute("usuario", oUsuario);
        } else {
            result = "Login o password incorrectos. No has entrado en la aplicación.";
        }
        return result;
    }

}
