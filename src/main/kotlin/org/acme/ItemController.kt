package org.acme

import io.smallrye.mutiny.Uni
import jakarta.ws.rs.Consumes
import jakarta.ws.rs.GET
import jakarta.ws.rs.Path
import jakarta.ws.rs.Produces
import jakarta.ws.rs.QueryParam
import jakarta.ws.rs.core.MediaType

@Path("/items")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
class ItemController(private val itemService: ItemService) {

    @GET
    @Path("/by-colors")
    fun findByColors(@QueryParam("color") colors: List<Color>): Uni<List<Item>> {
        return itemService.findBy(colors);
    }

    @GET
    @Path("/by-color")
    fun findByColor(@QueryParam("color") color: Color): Uni<List<Item>> {
        return itemService.findBy(color);
    }
}
