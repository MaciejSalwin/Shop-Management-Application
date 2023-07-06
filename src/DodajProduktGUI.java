import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DodajProduktGUI extends JFrame {

    private JTextField nameTextField;
    private JTextField priceTextField;
    private JTextField descriptionTextField;
    private JComboBox<String> categoryComboBox;
    private JComboBox<String> producerComboBox;

    private ProductGUI parentGUI;

    public DodajProduktGUI(ProductGUI parentGUI) {
        this.parentGUI = parentGUI;
        setTitle("Dodawanie nowego produktu");
        setPreferredSize(new Dimension(500, 400));

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        JPanel outerPanel = new JPanel();
        outerPanel.setLayout(new BorderLayout());
        outerPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel titlePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.GRAY);
                g.fillRect(0, 0, getWidth(), getHeight());
                g.setColor(Color.WHITE);
                g.setFont(new Font("Arial", Font.BOLD, 24));
                g.drawString("Dodaj Produkt", 20, 30);
            }
        };
        titlePanel.setPreferredSize(new Dimension(460, 60));

        outerPanel.add(titlePanel, BorderLayout.NORTH);

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(5, 2));
        formPanel.setBackground(Color.WHITE);
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        JLabel nameLabel = new JLabel("Nazwa:");
        nameTextField = new JTextField();
        JLabel priceLabel = new JLabel("Cena:");
        priceTextField = new JTextField();
        JLabel descriptionLabel = new JLabel("Opis:");
        descriptionTextField = new JTextField();
        JLabel categoryLabel = new JLabel("Kategoria:");
        categoryComboBox = new JComboBox<>(TypProduktu.getKategorie());
        JLabel producerLabel = new JLabel("Producent:");
        producerComboBox = new JComboBox<>(TypProduktu.getProducenci());

        formPanel.add(nameLabel);
        formPanel.add(nameTextField);
        formPanel.add(priceLabel);
        formPanel.add(priceTextField);
        formPanel.add(descriptionLabel);
        formPanel.add(descriptionTextField);
        formPanel.add(categoryLabel);
        formPanel.add(categoryComboBox);
        formPanel.add(producerLabel);
        formPanel.add(producerComboBox);

        outerPanel.add(formPanel, BorderLayout.CENTER);

        mainPanel.add(outerPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 20, 20, 20));

        JButton addButton = new JButton("Dodaj");
        addButton.setPreferredSize(new Dimension(100, 30));
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addProduct();
            }
        });
        buttonPanel.add(addButton);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);
        pack();
        setLocationRelativeTo(parentGUI);
    }

    private void addProduct() {
        String name = nameTextField.getText();
        String price = priceTextField.getText();
        String description = descriptionTextField.getText();
        String category = (String) categoryComboBox.getSelectedItem();
        String producer = (String) producerComboBox.getSelectedItem();

        if (name.isBlank() || price.isBlank() || description.isBlank() || category.isBlank() || producer.isBlank()) {
            JOptionPane.showMessageDialog(this, "Któraś z rubryk nie została wypełniona! Proszę ponownie wprowadzić dane do formularza.", "Błąd", JOptionPane.ERROR_MESSAGE);
        } else {
            boolean productExists = false;
            for (Produkt existingProduct : Produkt.getWszystkieProdukty()) {
                if (existingProduct.getNazwa().equals(name)
                        && existingProduct.getCena().equals(price)
                        && existingProduct.getSzczegółowyOpis().equals(description)
                        && existingProduct.getTypProduktu().getKategoria().equals(category)
                        && existingProduct.getTypProduktu().getProducent().equals(producer)) {
                    productExists = true;
                    break;
                }
            }

            if (productExists) {
                JOptionPane.showMessageDialog(this, "Wprowadzone dane produktu, które próbujesz dodać, są już wpisane do bazy danych produktów w sprzedaży.", "Błąd", JOptionPane.ERROR_MESSAGE);
            } else {
                for (Produkt existingProduct : Produkt.getWszystkieProdukty()) {
                    if (existingProduct.getNazwa().equals(name)
                            && existingProduct.getTypProduktu().getKategoria().equals(category)
                            && existingProduct.getTypProduktu().getProducent().equals(producer)) {
                        JOptionPane.showMessageDialog(this, "Produkt o podanej nazwie, kategorii i producencie już istnieje w bazie danych produktów w sprzedaży.", "Błąd", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }

                String[] productData = {name, price, description, category, producer};
                parentGUI.addProduct(productData);

                Produkt produkt = new Produkt(name, price, description, -1, null, TypProduktu.fromString(category, producer));
                Produkt.getWszystkieProdukty().add(produkt);
                Produkt.writeExtent();


                JOptionPane.showMessageDialog(this, "Proces dodawania produktu został zatwierdzony. Produkt trafił do bazy danych produktów w sprzedaży.", "Sukces", JOptionPane.INFORMATION_MESSAGE);
                dispose();
            }
        }
    }
}
