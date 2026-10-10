package Ressources;

import entities.Etudiant;
import entities.EtudiantList;
import entities.Option;
import metiers.EtudiantBusiness;
import metiers.OptionBusiness;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/etudiants")
public class EtudiantRessource {
    EtudiantBusiness etudiant=new EtudiantBusiness();
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addEtd(Etudiant e){
        if(etudiant.addEtudiant(e)){
            return Response.status(Response.Status.CREATED).entity(e).build();

        }
        return Response.status(Response.Status.NOT_FOUND).entity("erreur d ajout").build();
    }
    /* recuperation de la liste des etudiants par XML
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public Response getAllEtd() {
        List<Etudiant> liste = etudiant.getAllEtudiants();
        if (liste != null && !liste.isEmpty()) {
            EtudiantList wrapper = new EtudiantList(liste);
            return Response.status(Response.Status.OK).entity(wrapper).build();
        }
        return Response.status(Response.Status.NOT_FOUND).entity("liste vide").build();
    }*/
    @GET
    @Path("/list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllEtd(){
        if(etudiant.getAllEtudiants()!=null)
            return Response.status(Response.Status.OK).entity(etudiant.getAllEtudiants()).build();
        return Response.status(Response.Status.NOT_FOUND).entity("liste vide").build();
    }
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEtdById(@PathParam("id")String id){
        if(etudiant.getEtudiantByIdentifiant(id)!=null){
            return Response.status(Response.Status.OK).entity(etudiant.getEtudiantByIdentifiant(id)).build();
        }
        return Response.status(Response.Status.NOT_FOUND).entity("erreur").build();
    }
    @DELETE
    @Path("/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteETD(@PathParam("id")String id){
        if(etudiant.deleteEtudiant(id)){
            return Response.status(Response.Status.OK).entity("deleted with success").build();
        }
        return Response.status(Response.Status.NOT_FOUND).entity("erreur de suppression").build();
    }
    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateETD(@PathParam("id")String id, Etudiant e){
        if(e!=null && etudiant.updateEtudiant(id,e)){
            return Response.status(Response.Status.OK).entity("updated with success").build();
        }
        return Response.status(Response.Status.NOT_FOUND).entity("erreur de modification").build();
    }
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public Response getByOption(@QueryParam("code")int codeOption){
        OptionBusiness opt =new OptionBusiness();
        Option option =opt.getOptionByCode(codeOption);
        if (option!=null){
            List<Etudiant> list=etudiant.getEtudiantsByOption(option);
            if(list!=null && !list.isEmpty()){
                EtudiantList tab=new EtudiantList(list);
                return Response.status(Response.Status.OK).entity(tab).build();

            }
        }
        return Response.status(Response.Status.NOT_FOUND).entity("erreur de recuperation").build();

    }



}
