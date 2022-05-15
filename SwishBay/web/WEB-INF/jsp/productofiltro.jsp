<%-- 
    Document   : productofiltro
    Created on : Apr 16, 2022, 9:19:09 PM
    Author     : Miguel Oña Guerrero
--%>

<%@page import="swishbay.dto.CategoriaDTO"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
                  <%
                      String action = (String)request.getAttribute("servlet");
                      Double mayorPrecio = (Double)request.getAttribute("mayorPrecio");
                      Double precio = (Double)request.getAttribute("precio");
                      if(precio == null || precio > mayorPrecio){
                          precio = mayorPrecio;
                      }
                  %>
                  <p class=" mx-2">Precio máximo</p>
                  <form action="<%=action %>" method="POST" class="d-flex ">   
                      <input class="me-2 mb-3" type="range" id="points" name="filtroPrecio" min="0" max="<%= mayorPrecio %>" value="<%= precio %>">
                      <p class="me-2"><%= precio %>€</p>
                      <input type="hidden" name="precioMaximo" value="<%= mayorPrecio %>" />
                    <div class="col-sm-4">
                        <select class="form-select px-2" id="filtroCategoria" name="filtroCategoria">         
                            <%
                              List<CategoriaDTO> categorias = (List) request.getAttribute("categorias");
                              String filtroCategoria = (String) request.getAttribute("selected");
                            %>
                            <option <%= (filtroCategoria==null || filtroCategoria.equals("Categoria")) ? "selected":"" %> value="Categoria">Categoría </option>
                            <%
                              for (CategoriaDTO categoria : categorias){
                                
                            %>     
                            <option <%= (filtroCategoria!=null && filtroCategoria.equals(categoria.getNombre())) ? "selected":"" %> value="<%=categoria.getNombre()%>"><%=categoria.getNombre()%> </option>
                            <%  
                              }
                            %>
                        </select>
                    </div>
                    <input class="form-control me-2 mx-2" type="search" placeholder="Buscar" name="filtro" aria-label="Search">
                    <input class="btn btn-outline-success" type="submit" value="Buscar">
                  </form>
