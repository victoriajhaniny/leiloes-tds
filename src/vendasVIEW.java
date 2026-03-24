import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class vendasVIEW extends JFrame {

    private JTable tabelaVendas;

    public vendasVIEW() {
        setTitle("Produtos Vendidos");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        tabelaVendas = new JTable();
        tabelaVendas.setModel(new DefaultTableModel(
            new Object [][] {},
            new String [] {"ID", "Nome", "Valor", "Status"}
        ));

        add(new JScrollPane(tabelaVendas));

        listarProdutosVendidos();
    }

    private void listarProdutosVendidos() {
        try {
            ProdutosDAO produtosdao = new ProdutosDAO();

            DefaultTableModel model = (DefaultTableModel) tabelaVendas.getModel();
            model.setNumRows(0);

            ArrayList<ProdutosDTO> listagem = produtosdao.listarProdutosVendidos();

            for (int i = 0; i < listagem.size(); i++) {
                model.addRow(new Object[]{
                    listagem.get(i).getId(),
                    listagem.get(i).getNome(),
                    listagem.get(i).getValor(),
                    listagem.get(i).getStatus()
                });
            }
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(null, "Erro ao listar vendidos: " + e.getMessage());
        }
    }
}