<%-- 
    Document   : productofiltro
    Created on : Apr 16, 2022, 9:19:09 PM
    Author     : Miguel Oña Guerrero
--%>

<%@page import="swishbay.dto.CategoriaDTO"%>
<%@page import="java.util.List"%>
<%@page import="swishbay.entity.Categoria"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
                  <%
                      String action = (String)request.getAttribute("servlet");
                  %>
                  <form action="<%=action %>" method="POST" class="d-flex">
                    <div class="col-sm-4">
                        <select class="form-select px-2" id="filtroCategoria" name="filtroCategoria">         
                            <%
                              List<CategoriaDTO> categorias = (List) request.getAttribute("categorias");
                              String filtroCategoria = (String) request.getAttribute("selected");
                             %>
                             <option <%= (filtroCategoria==null || filtroCategoria.equals("Categoria")) ? "selected":"" %> value="Categoria">Categoría </option>
                             <%
                              for (CategoriaDTO c : categorias){
                                
                            %>     
                                <option <%= (filtroCategoria!=null && filtroCategoria.equals(c.getNombre())) ? "selected":"" %> value="<%=c.getNombre()%>"><%=c.getNombre()%> </option>
                           <%  
                              }
                           %>
                        </select>
                    </div>
                    <input class="form-control me-2 mx-2" type="search" placeholder="Buscar" name="filtro" aria-label="Search">
                    <input class="btn btn-outline-success" type="submit" value="Buscar">
                  </form>
