import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import org.bson.Document;

import java.util.Arrays;

public class UsuarioOperations {

    private MongoDatabase database;
    private MongoCollection<Document> collection;

    public UsuarioOperations() {
        MongoDBConnection connection = new MongoDBConnection();
        this.database = connection.getDatabase();
        // Nome da coleção, pode ser "usuarios"
        this.collection = database.getCollection("usuarios");
    }

    private void inserirUsuarios() {
        Usuario u1 = new Usuario("Alice", 25);
        Usuario u2 = new Usuario("Bob", 30);
        Usuario u3 = new Usuario("Charlie", 35);

        collection.insertMany(Arrays.asList(
                u1.toDocument(),
                u2.toDocument(),
                u3.toDocument()
        ));

        System.out.println("Usuários inseridos.");
    }

    private void consultarUsuarios() {
        System.out.println("Listando usuários:");
        try (MongoCursor<Document> cursor = collection.find().iterator()) {
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                Usuario usuario = Usuario.fromDocument(doc);
                System.out.println(usuario);
            }
        }
    }

    private void atualizarBob() {
        collection.updateOne(
                Filters.eq("nome", "Bob"),
                Updates.set("idade", 32)
        );
        System.out.println("Idade de Bob atualizada para 32.");
    }

    private void deletarCharlie() {
        collection.deleteOne(Filters.eq("nome", "Charlie"));
        System.out.println("Charlie removido.");
    }

    public static void main(String[] args) {
        UsuarioOperations operations = new UsuarioOperations();

        // Insere 3 registros
        operations.inserirUsuarios();
        operations.consultarUsuarios();

        // Atualiza Bob
        operations.atualizarBob();
        operations.consultarUsuarios();

        // Remove Charlie
        operations.deletarCharlie();
        operations.consultarUsuarios();
    }
}