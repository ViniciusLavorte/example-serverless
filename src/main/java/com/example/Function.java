package com.example;

import java.util.*;
import com.microsoft.azure.functions.annotation.*;
import com.microsoft.azure.functions.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
//verificando

public class Function {
    @FunctionName("funcaocriarcidade")
    public Cidade criar(@HttpTrigger(name = "restcriarcidade", methods = {HttpMethod.POST}, route = "cidade") Cidade c) {
        c.setId(new Long(1));

        // Conjunto operações business
        return c;
    }
    @FunctionName("funcaolercidade")
    public List<Cidade> ler(@HttpTrigger(name = "restlercidade", methods = {HttpMethod.GET}, route = "cidade") String x) {

        return Stream.of(
                new Cidade(1L, "Uberlandia", new Estado(1L, "Minas Gerais")),
                new Cidade(2L, "Carapicuiba", new Estado(2L, "São Paulo")),
                new Cidade(3L, "Curitiba", new Estado(1L, "Paraná"))
        ).collect(Collectors.toList());
    }

     @FunctionName ("funcaoalterarcidade")
    public Cidade alterar (
        @HttpTrigger (
            name = "restalterarcidade",
            methods = {HttpMethod.PUT},
            route = "cidade"
        )
        Cidade c) {
        c.setEstado(new Estado(c.getEstado().getId() + 10L, c.getEstado().getNome()));

        return c;
    }
    
     @FunctionName ("funcaoapagarcidade")
    public int apagar (
        @HttpTrigger (
            name = "restapagarcidade",
            methods = {HttpMethod.DELETE},
            route = "cidade/{id}"
        )
        @BindingName ("id") Long id) {
        System.out.println(String.format("Id: %d", id));

        return 200;
   }
}
