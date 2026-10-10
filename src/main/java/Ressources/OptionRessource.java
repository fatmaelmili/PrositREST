package Ressources;

import entities.Option;
import metiers.OptionBusiness;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;


@Path("/options")
public class OptionRessource {
    OptionBusiness optionBusiness = new OptionBusiness();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Option> getList() {

        return optionBusiness.getListeOptions();
    }

    @GET
    @Path("/{code}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getListById(@PathParam("code") int code) {
        Option opt = optionBusiness.getOptionByCode(code);
        if (opt != null)
            return Response.status(Response.Status.OK).entity(opt).build();
        return Response.status(Response.Status.NOT_FOUND).entity("opt not found").build();


    }

    @GET
    @Produces(MediaType.APPLICATION_XML)
    public Response getBySemestre(@QueryParam("s") int semestre) {
        List<Option> opt = optionBusiness.getOptionsBySemestre(semestre);
        if (opt != null)
            return Response.status(Response.Status.OK).entity(opt).build();
        return Response.status(Response.Status.NOT_FOUND).entity("aucun semestre").build();


    }

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addOption(Option opt) {
        if (optionBusiness.addOption(opt))
            return Response.status(Response.Status.CREATED).entity("added with success").build();
        return Response.status(Response.Status.BAD_REQUEST).entity("echec d ajout").build();

    }

    @PUT
    @Path("/{code}")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateOption(@PathParam("code") int code, Option opt) {
        if (optionBusiness.getOptionByCode(code) != null) {
            optionBusiness.updateOption(code, opt);
            return Response.status(Response.Status.OK).entity("updated with success").build();
        }
        return Response.status(Response.Status.NOT_FOUND).entity("echec ").build();

    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteOption(@PathParam("id") int id) {
        if (optionBusiness.deleteOption(id))
            return Response.status(Response.Status.OK).entity("succes delete").build();
        return Response.status(Response.Status.NOT_FOUND).entity("echec delete").build();


    }
}