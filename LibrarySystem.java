import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class LibrarySystem {
    private List<Book> books;
    private List<Patron> patrons;
    private List<CheckoutRecord> checkouts;

    public LibrarySystem() {
        books = new ArrayList<>();
        patrons = new ArrayList<>();
        checkouts = new ArrayList<>();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LibrarySystem library = new LibrarySystem();
            library.createAndShowGUI();
        });
    }

    private void createAndShowGUI() {
        JFrame frame = new JFrame("Library Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(new BorderLayout());

        // Create menu bar
        JMenuBar menuBar = new JMenuBar();
        
        // Books menu
        JMenu booksMenu = new JMenu("Books");
        JMenuItem addBookItem = new JMenuItem("Add Book");
        JMenuItem viewBooksItem = new JMenuItem("View Books");
        booksMenu.add(addBookItem);
        booksMenu.add(viewBooksItem);
        
        // Patrons menu
        JMenu patronsMenu = new JMenu("Patrons");
        JMenuItem addPatronItem = new JMenuItem("Add Patron");
        JMenuItem viewPatronsItem = new JMenuItem("View Patrons");
        patronsMenu.add(addPatronItem);
        patronsMenu.add(viewPatronsItem);
        
        // Checkouts menu
        JMenu checkoutsMenu = new JMenu("Checkouts");
        JMenuItem checkoutBookItem = new JMenuItem("Checkout Book");
        JMenuItem returnBookItem = new JMenuItem("Return Book");
        JMenuItem viewCheckoutsItem = new JMenuItem("View Checkouts");
        checkoutsMenu.add(checkoutBookItem);
        checkoutsMenu.add(returnBookItem);
        checkoutsMenu.add(viewCheckoutsItem);
        
        menuBar.add(booksMenu);
        menuBar.add(patronsMenu);
        menuBar.add(checkoutsMenu);
        
        frame.setJMenuBar(menuBar);

        // Welcome panel
        JPanel welcomePanel = new JPanel(new BorderLayout());
        JLabel welcomeLabel = new JLabel("Welcome to Library Management System", JLabel.CENTER);
        welcomeLabel.setFont(new Font("Serif", Font.BOLD, 24));
        welcomePanel.add(welcomeLabel, BorderLayout.CENTER);
        frame.add(welcomePanel, BorderLayout.CENTER);

        // Add action listeners
        addBookItem.addActionListener(e -> showAddBookDialog(frame));
        viewBooksItem.addActionListener(e -> showBooksDialog(frame));
        addPatronItem.addActionListener(e -> showAddPatronDialog(frame));
        viewPatronsItem.addActionListener(e -> showPatronsDialog(frame));
        checkoutBookItem.addActionListener(e -> showCheckoutDialog(frame));
        returnBookItem.addActionListener(e -> showReturnDialog(frame));
        viewCheckoutsItem.addActionListener(e -> showCheckoutsDialog(frame));

        frame.setVisible(true);
    }

    private void showAddBookDialog(JFrame parent) {
        JDialog dialog = new JDialog(parent, "Add New Book", true);
        dialog.setSize(400, 300);
        dialog.setLayout(new GridLayout(6, 2));

        JLabel titleLabel = new JLabel("Title:");
        JTextField titleField = new JTextField();
        JLabel authorLabel = new JLabel("Author:");
        JTextField authorField = new JTextField();
        JLabel isbnLabel = new JLabel("ISBN:");
        JTextField isbnField = new JTextField();
        JLabel yearLabel = new JLabel("Publication Year:");
        JTextField yearField = new JTextField();
        JLabel copiesLabel = new JLabel("Copies Available:");
        JTextField copiesField = new JTextField();

        JButton addButton = new JButton("Add Book");
        JButton cancelButton = new JButton("Cancel");

        dialog.add(titleLabel);
        dialog.add(titleField);
        dialog.add(authorLabel);
        dialog.add(authorField);
        dialog.add(isbnLabel);
        dialog.add(isbnField);
        dialog.add(yearLabel);
        dialog.add(yearField);
        dialog.add(copiesLabel);
        dialog.add(copiesField);
        dialog.add(addButton);
        dialog.add(cancelButton);

        addButton.addActionListener(e -> {
            try {
                String title = titleField.getText();
                String author = authorField.getText();
                String isbn = isbnField.getText();
                int year = Integer.parseInt(yearField.getText());
                int copies = Integer.parseInt(copiesField.getText());

                Book book = new Book(title, author, isbn, year, copies);
                books.add(book);
                JOptionPane.showMessageDialog(dialog, "Book added successfully!");
                dialog.dispose();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(dialog, "Please enter valid numbers for year and copies.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        cancelButton.addActionListener(e -> dialog.dispose());

        dialog.setVisible(true);
    }

    private void showBooksDialog(JFrame parent) {
        JDialog dialog = new JDialog(parent, "View Books", true);
        dialog.setSize(600, 400);
        
        String[] columnNames = {"Title", "Author", "ISBN", "Year", "Available Copies"};
        Object[][] data = new Object[books.size()][5];
        
        for (int i = 0; i < books.size(); i++) {
            Book book = books.get(i);
            data[i][0] = book.getTitle();
            data[i][1] = book.getAuthor();
            data[i][2] = book.getIsbn();
            data[i][3] = book.getPublicationYear();
            data[i][4] = book.getAvailableCopies();
        }
        
        JTable table = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(table);
        
        dialog.add(scrollPane);
        dialog.setVisible(true);
    }

    private void showAddPatronDialog(JFrame parent) {
        JDialog dialog = new JDialog(parent, "Add New Patron", true);
        dialog.setSize(400, 250);
        dialog.setLayout(new GridLayout(5, 2));

        JLabel nameLabel = new JLabel("Name:");
        JTextField nameField = new JTextField();
        JLabel idLabel = new JLabel("ID:");
        JTextField idField = new JTextField();
        JLabel emailLabel = new JLabel("Email:");
        JTextField emailField = new JTextField();
        JLabel phoneLabel = new JLabel("Phone:");
        JTextField phoneField = new JTextField();

        JButton addButton = new JButton("Add Patron");
        JButton cancelButton = new JButton("Cancel");

        dialog.add(nameLabel);
        dialog.add(nameField);
        dialog.add(idLabel);
        dialog.add(idField);
        dialog.add(emailLabel);
        dialog.add(emailField);
        dialog.add(phoneLabel);
        dialog.add(phoneField);
        dialog.add(addButton);
        dialog.add(cancelButton);

        addButton.addActionListener(e -> {
            String name = nameField.getText();
            String id = idField.getText();
            String email = emailField.getText();
            String phone = phoneField.getText();

            Patron patron = new Patron(name, id, email, phone);
            patrons.add(patron);
            JOptionPane.showMessageDialog(dialog, "Patron added successfully!");
            dialog.dispose();
        });

        cancelButton.addActionListener(e -> dialog.dispose());

        dialog.setVisible(true);
    }

    private void showPatronsDialog(JFrame parent) {
        JDialog dialog = new JDialog(parent, "View Patrons", true);
        dialog.setSize(600, 400);
        
        String[] columnNames = {"Name", "ID", "Email", "Phone"};
        Object[][] data = new Object[patrons.size()][4];
        
        for (int i = 0; i < patrons.size(); i++) {
            Patron patron = patrons.get(i);
            data[i][0] = patron.getName();
            data[i][1] = patron.getId();
            data[i][2] = patron.getEmail();
            data[i][3] = patron.getPhone();
        }
        
        JTable table = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(table);
        
        dialog.add(scrollPane);
        dialog.setVisible(true);
    }

    private void showCheckoutDialog(JFrame parent) {
        if (books.isEmpty() || patrons.isEmpty()) {
            JOptionPane.showMessageDialog(parent, 
                books.isEmpty() ? "No books available for checkout." : "No patrons registered.",
                "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        JDialog dialog = new JDialog(parent, "Checkout Book", true);
        dialog.setSize(400, 200);
        dialog.setLayout(new GridLayout(4, 2));

        JLabel patronLabel = new JLabel("Select Patron:");
        JComboBox<Patron> patronCombo = new JComboBox<>(patrons.toArray(new Patron[0]));
        JLabel bookLabel = new JLabel("Select Book:");
        JComboBox<Book> bookCombo = new JComboBox<>(books.stream()
                .filter(book -> book.getAvailableCopies() > 0)
                .toArray(Book[]::new));
        
        if (bookCombo.getItemCount() == 0) {
            JOptionPane.showMessageDialog(dialog, "No books available for checkout.", "Error", JOptionPane.ERROR_MESSAGE);
            dialog.dispose();
            return;
        }

        JLabel dueDateLabel = new JLabel("Due Date (days):");
        JTextField dueDateField = new JTextField("14");

        JButton checkoutButton = new JButton("Checkout");
        JButton cancelButton = new JButton("Cancel");

        dialog.add(patronLabel);
        dialog.add(patronCombo);
        dialog.add(bookLabel);
        dialog.add(bookCombo);
        dialog.add(dueDateLabel);
        dialog.add(dueDateField);
        dialog.add(checkoutButton);
        dialog.add(cancelButton);

        checkoutButton.addActionListener(e -> {
            try {
                Patron patron = (Patron) patronCombo.getSelectedItem();
                Book book = (Book) bookCombo.getSelectedItem();
                int dueDays = Integer.parseInt(dueDateField.getText());

                if (book.getAvailableCopies() <= 0) {
                    JOptionPane.showMessageDialog(dialog, "No copies available for this book.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                CheckoutRecord record = new CheckoutRecord(patron, book, dueDays);
                checkouts.add(record);
                book.checkout();
                JOptionPane.showMessageDialog(dialog, "Book checked out successfully!");
                dialog.dispose();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(dialog, "Please enter a valid number for due date.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        cancelButton.addActionListener(e -> dialog.dispose());

        dialog.setVisible(true);
    }

    private void showReturnDialog(JFrame parent) {
        if (checkouts.isEmpty()) {
            JOptionPane.showMessageDialog(parent, "No books currently checked out.", "Information", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        JDialog dialog = new JDialog(parent, "Return Book", true);
        dialog.setSize(400, 150);
        dialog.setLayout(new GridLayout(3, 2));

        JLabel checkoutLabel = new JLabel("Select Checkout:");
        JComboBox<CheckoutRecord> checkoutCombo = new JComboBox<>(checkouts.toArray(new CheckoutRecord[0]));

        JButton returnButton = new JButton("Return");
        JButton cancelButton = new JButton("Cancel");

        dialog.add(checkoutLabel);
        dialog.add(checkoutCombo);
        dialog.add(new JLabel()); // empty cell
        dialog.add(new JLabel()); // empty cell
        dialog.add(returnButton);
        dialog.add(cancelButton);

        returnButton.addActionListener(e -> {
            CheckoutRecord record = (CheckoutRecord) checkoutCombo.getSelectedItem();
            record.getBook().returnBook();
            checkouts.remove(record);
            JOptionPane.showMessageDialog(dialog, "Book returned successfully!");
            dialog.dispose();
        });

        cancelButton.addActionListener(e -> dialog.dispose());

        dialog.setVisible(true);
    }

    private void showCheckoutsDialog(JFrame parent) {
        JDialog dialog = new JDialog(parent, "View Checkouts", true);
        dialog.setSize(700, 400);
        
        String[] columnNames = {"Patron", "Book", "Checkout Date", "Due Date", "Status"};
        Object[][] data = new Object[checkouts.size()][5];
        
        for (int i = 0; i < checkouts.size(); i++) {
            CheckoutRecord record = checkouts.get(i);
            data[i][0] = record.getPatron().getName();
            data[i][1] = record.getBook().getTitle();
            data[i][2] = record.getCheckoutDate();
            data[i][3] = record.getDueDate();
            data[i][4] = "Checked Out";
        }
        
        JTable table = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(table);
        
        dialog.add(scrollPane);
        dialog.setVisible(true);
    }
}