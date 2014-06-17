package UI;

import BLL.addrBookBLL;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class addrBookDialog extends JDialog {

    private static JTable table;
    private static JLabel IDLabel;
    private static JTextField nameText;
    private static JTextField sexText;
    private static JTextField phoneText;
    private static JTextField emailText;
    private static JTextField addressText;
    String[] items={"����","�Ա�","�绰","�����ʼ�","��ַ"};

    public addrBookDialog(MainFrame mainFrame) {
        super(mainFrame,true);
        setTitle("ͨѶ������");

        JPanel contentPanel=new JPanel(new BorderLayout());
        this.setContentPane(contentPanel);

        JScrollPane tablePanel=new JScrollPane();
        contentPanel.add(tablePanel,BorderLayout.CENTER);
        table= addrBookBLL.getJTable();
        final DefaultTableModel tableModel= (DefaultTableModel) table.getModel();
        tablePanel.setViewportView(table);


        JPanel controlPanel=new JPanel(new BorderLayout());
        contentPanel.add(controlPanel,BorderLayout.SOUTH);


        JPanel textPanel=new JPanel(new GridLayout(2,2));
        controlPanel.add(textPanel,BorderLayout.NORTH);

        textPanel.add(new JLabel("�����������: "));  //����ı���
        IDLabel=new JLabel("���");
        textPanel.add(IDLabel);


        textPanel.add(new JLabel("������������: "));
        nameText=new JTextField(10);
        textPanel.add(nameText);

        textPanel.add(new JLabel("���������Ա�: "));
        sexText=new JTextField(10);
        textPanel.add(sexText);



        textPanel.add(new JLabel("���������绰: "));
        phoneText=new JTextField(10);
        textPanel.add(phoneText);


        textPanel.add(new JLabel("�������������ʼ�: "));
        emailText=new JTextField(10);
        textPanel.add(emailText);

        textPanel.add(new JLabel("����������ַ: "));
        addressText=new JTextField(10);
        textPanel.add(addressText);


        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);  //��ѡ
        table.addMouseListener(new MouseAdapter(){    //����¼�
            public void mouseClicked(MouseEvent e){
                int selectedRow = table.getSelectedRow(); //���ѡ��������
                Object o1 = tableModel.getValueAt(selectedRow, 0);

                Object o2 = tableModel.getValueAt(selectedRow, 1);
                Object o3 = tableModel.getValueAt(selectedRow, 2);
                Object o4 = tableModel.getValueAt(selectedRow, 3);
                Object o5 = tableModel.getValueAt(selectedRow, 4);
                Object o6 = tableModel.getValueAt(selectedRow, 5);
                IDLabel.setText(o1.toString());  //���ı���ֵ
                nameText.setText(o2.toString());
                sexText.setText(o3.toString());
                phoneText.setText(o4.toString());
                emailText.setText(o5.toString());
                addressText.setText(o6.toString());
            }
        });

        JPanel buttonPanel=new JPanel();            //��Ӱ�ť
        controlPanel.add(buttonPanel,BorderLayout.SOUTH);

        final JButton addButton = new JButton("���");   //��Ӱ�ť
        addButton.addActionListener(new ActionListener(){//����¼�
            public void actionPerformed(ActionEvent e){

                int resultID=-1;
                resultID=addrBookBLL.add(nameText.getText(),sexText.getText(),phoneText.getText(),emailText.getText(),addressText.getText());
                String []rowValues = {String.valueOf(resultID),nameText.getText(),sexText.getText(),phoneText.getText(),emailText.getText(),addressText.getText()};

                tableModel.addRow(rowValues);  //���һ��
                int rowCount = table.getRowCount() +1;   //��������1
                JOptionPane.showMessageDialog(null,"��ӳɹ�");

            }
        });
        buttonPanel.add(addButton);



        final JButton updateButton = new JButton("�޸�");   //��Ӱ�ť
        updateButton.addActionListener(new ActionListener(){//����¼�
            public void actionPerformed(ActionEvent e){
                int selectedRow = table.getSelectedRow();//���ѡ���е�����
                if(selectedRow!= -1)   //�Ƿ����ѡ����
                {
                    //�޸�ָ����ֵ��

                    tableModel.setValueAt(nameText.getText(), selectedRow, 1);
                    tableModel.setValueAt(sexText.getText(), selectedRow, 2);
                    tableModel.setValueAt(phoneText.getText(), selectedRow, 3);
                    tableModel.setValueAt(emailText.getText(), selectedRow, 4);
                    tableModel.setValueAt(addressText.getText(), selectedRow, 5);
                }

                addrBookBLL.update(IDLabel.getText(),nameText.getText(),sexText.getText(),phoneText.getText(),emailText.getText(),addressText.getText());
                JOptionPane.showMessageDialog(null,"�޸ĳɹ�");
            }
        });
        buttonPanel.add(updateButton);


        final JButton delButton = new JButton("ɾ��");
        delButton.addActionListener(new ActionListener(){//����¼�
            public void actionPerformed(ActionEvent e){
                String ID;
                int selectedRow = table.getSelectedRow();//���ѡ���е�����
                ID=(String)tableModel.getValueAt(selectedRow, 0);
                if(selectedRow!=-1)  //����ѡ����
                {
                    tableModel.removeRow(selectedRow);  //ɾ����
                }

                addrBookBLL.delete(ID);
                JOptionPane.showMessageDialog(null,"ɾ���ɹ�");
            }
        });
        buttonPanel.add(delButton);

        final JButton searchButton = new JButton("����");
        searchButton.addActionListener(new ActionListener(){//����¼�
            public void actionPerformed(ActionEvent e){
                int selectedItem;
                String searchText;
                new searchDialog(addrBookDialog.this,items);
                selectedItem=searchDialog.getSelectedItem();
                searchText=searchDialog.getSearchText();
                highlightRow(selectedItem,searchText);
            }

            private void highlightRow(final int selectedItem, final String searchText) {    //������ʾ�����

                final DefaultTableCellRenderer tcr = new DefaultTableCellRenderer()
                {

                    public Component getTableCellRendererComponent(JTable table, Object value,
                                                                   boolean isSelected, boolean hasFocus, int row, int column)
                    {
                        if(tableModel.getValueAt(row, selectedItem).toString().equals(searchText)){
                            setBackground(Color.yellow);
                        }
                        else
                            setBackground(Color.white);
                        return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                    }
                };
                for(int i=0;i<items.length;i++)
                {
                    table.getColumn(items[i]).setCellRenderer(tcr);
                }
                tableModel.fireTableDataChanged();

            }
        });
        buttonPanel.add(searchButton);
        searchButton.setToolTipText("���Ͻ����������������������޸���");



        setSize(800, 400);
        this.setLocationRelativeTo(null);
        setVisible(true);


    }

}
