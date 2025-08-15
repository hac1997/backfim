package org.example.HTTP;


import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

// Um tratador deve implementar a interface HttpHandler
class MeuHandler implements HttpHandler {

    // O método handle é chamado para processar uma requisição.
    // A requisição é representada por um objeto HttpExchange
    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        // obtém um OutputStream para gerar o corpo da mensagem de resposta
        OutputStream outputStream = httpExchange.getResponseBody();

        // Esta é a resposta a requisições tratadas por este HttpHandler !
        String htmlResponse = "Hello world !";

       // Define o status da resposta, e o comprimento do corpo da mensagem  (em bytes)
        httpExchange.sendResponseHeaders(200, htmlResponse.length());

        // escreve o corpo da resposta
        outputStream.write(htmlResponse.getBytes());
        outputStream.flush();
        outputStream.close();
    }
}

// Um tratador deve implementar a interface HttpHandler
class MeuHandlerPortugues implements HttpHandler {

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        OutputStream outputStream = httpExchange.getResponseBody();

        String htmlResponse = "Ola mundo !";

        httpExchange.sendResponseHeaders(200, htmlResponse.length());
        outputStream.write(htmlResponse.getBytes());
        outputStream.flush();
        outputStream.close();
    }
}

public class Main {

    public static void main(String[] args) throws Exception {
        // Cria um servidor HTTP, que recebe requisições em localhost no port 8080 (e com backlog 0)        
        HttpServer srv = HttpServer.create(new InetSocketAddress("localhost", 8080), 0);

        srv.createContext("/", new MeuHandler());
        srv.createContext("/portugues", new MeuHandlerPortugues());

        // Executa o servidor HTTP
        srv.start();

    }
}