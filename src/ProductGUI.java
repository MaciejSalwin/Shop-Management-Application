import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ProductGUI extends JFrame {

    private JTable productTable;
    private DefaultTableModel productTableModel;

    public ProductGUI() {
        setTitle("Lista produktów");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(1000, 800));

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(Color.WHITE);
        headerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        headerPanel.setLayout(new BorderLayout());

        JLabel headerLabel = new JLabel("Lista produktów w sprzedaży");
        headerLabel.setFont(new Font("Arial", Font.BOLD, 18));
        headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        headerPanel.add(headerLabel, BorderLayout.CENTER);

        mainPanel.add(headerPanel, BorderLayout.NORTH);

        JPanel productContainerPanel = new JPanel();
        productContainerPanel.setBackground(Color.LIGHT_GRAY);
        productContainerPanel.setLayout(new BorderLayout());
        productContainerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        productTableModel = new DefaultTableModel();
        productTableModel.addColumn("Nazwa");
        productTableModel.addColumn("Cena");
        productTableModel.addColumn("Opis");
        productTableModel.addColumn("Kategoria");
        productTableModel.addColumn("Producent");

        productTable = new JTable(productTableModel);
        productTable.setEnabled(false);
        productTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        productTable.getColumnModel().getColumn(0).setPreferredWidth(200);
        productTable.getColumnModel().getColumn(1).setPreferredWidth(100);
        productTable.getColumnModel().getColumn(2).setPreferredWidth(300);
        productTable.getColumnModel().getColumn(3).setPreferredWidth(150);
        productTable.getColumnModel().getColumn(4).setPreferredWidth(150);

        JScrollPane scrollPane = new JScrollPane(productTable);
        productContainerPanel.add(scrollPane, BorderLayout.CENTER);

        mainPanel.add(productContainerPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

        JButton addProductButton = new JButton("Dodaj nowy produkt");
        addProductButton.setPreferredSize(new Dimension(150, 30));
        addProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openAddProductForm();
            }
        });
        buttonPanel.add(addProductButton);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);
        pack();
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.decode("#ADD8E6"));


        Produkt.readExtent();


        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                Produkt.writeExtent();
            }
        });
    }

    public void loadProductsFromBinaryFile() {
        Produkt.readExtent();

        for (Produkt produkt : Produkt.getWszystkieProdukty()) {
            String[] productData = {
                    produkt.getNazwa(),
                    produkt.getCena(),
                    produkt.getSzczegółowyOpis(),
                    produkt.getTypProduktu().getKategoria(),
                    produkt.getTypProduktu().getProducent()
            };
            productTableModel.addRow(productData);
        }
    }

    private void openAddProductForm() {
        DodajProduktGUI addProductForm = new DodajProduktGUI(this);
        addProductForm.setVisible(true);
    }

    public void addProduct(String[] productData) {
        productTableModel.addRow(productData);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                ProductGUI productGUI = new ProductGUI();
                productGUI.setVisible(true);
            }
        });
    }
}
