import java.sql.*;

public class Vetores  {
    private int[] message_id = new int[10];
    private String[] chat_id = new String[10];
    private int msg_id,identifier;
    private String cht_id;


    public Vetores(int identifier) {
        this.identifier = identifier;
    }

    public Vetores(int msg_id, String cht_id) {
        this.msg_id = msg_id;
        this.cht_id = cht_id;
        this.identifier++;
    }

    public Vetores(){

    }
    public void abrirConexao(){
        try {
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            String db = "jdbc:odbc:Driver={Microsoft Access Driver (*.mdb)};DBQ=BDBOT.mdb;";
            Connection conn = DriverManager.getConnection(db);
            Statement s = conn.createStatement();
            String sel = "select * from dados";
            s.execute(sel);
            ResultSet rs =s.getResultSet();
            System.out.print(rs.next());
        }catch (Exception e){
            e.printStackTrace();
        }
    }




    public int[] getMessage_id() {
        return message_id;
    }

    public void setMessage_id(int[] message_id) {
        this.message_id = message_id;
    }

    public String[] getChat_id() {
        return chat_id;
    }

    public void setChat_id(String[] chat_id) {
        this.chat_id = chat_id;
    }
}
