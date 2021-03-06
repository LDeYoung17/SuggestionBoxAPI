package dev.ranieri.controllers;


import com.google.gson.Gson;
import dev.ranieri.daos.SuggestionDaoLocal;
import dev.ranieri.entities.Suggestion;
import dev.ranieri.services.SuggestionService;
import dev.ranieri.services.SuggestionServiceImpl;
import io.javalin.http.Handler;

public class SuggestionController {

    private SuggestionService suggestionService = new SuggestionServiceImpl(new SuggestionDaoLocal());

    public Handler getAllSuggestions = ctx -> {
        Gson gson = new Gson();
        String json = gson.toJson(this.suggestionService.getAllSuggestions());
        ctx.result(json);
    };

    public Handler getSuggestionById = ctx -> {
        int id = Integer.parseInt(ctx.pathParam("id"));
        Gson gson = new Gson();
        String json = gson.toJson(this.suggestionService.getSuggestionById(id));
        ctx.result(json);
    };

    public  Handler createSuggestion = ctx -> {
        Gson gson = new Gson();
        String body = ctx.body();
        Suggestion suggestion = gson.fromJson(body,Suggestion.class);
        this.suggestionService.createSuggestion(suggestion);
        ctx.status(201);
        ctx.result("You created a suggestion");

    };


}
