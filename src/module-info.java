module sistema.loja.de.racao {
    requires java.persistence;
    requires javafx.controls;
    requires java.sql;

    exports br.com.ryanlucas.sistemalojaderacao.view;
    opens br.com.ryanlucas.sistemalojaderacao.model;
    exports br.com.ryanlucas.sistemalojaderacao.model;
}