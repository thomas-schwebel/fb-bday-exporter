package fr.tschwebel.handler;

import fr.tschwebel.template.HtmlTemplate;
import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.Headers;

public class HtmlHandler implements HttpHandler {

    private final HtmlTemplate template;

    public HtmlHandler(HtmlTemplate template) {
        this.template = template;
    }

    @Override
    public void handleRequest(HttpServerExchange exchange) throws Exception {
        exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "text/html");
        exchange.getResponseSender().send(template.generateHTML());
    }
}
