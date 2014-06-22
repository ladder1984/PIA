package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.*;
import javax.swing.table.*;
import BLL.pwdBLL;

public class pwdDialog extends JDialog{

    private static JTable table;        //��ʾ��������ı���
    private static JLabel IDLabel;
    private static JTextField nameText;
    private static JTextField pwdText;
    private static JTextField commentsText;
    String[] items={"����","����","��ע"};    //�������У����ڴ��ݸ������Ի���

    public pwdDialog(MainFrame mainFrame) {
        super(mainFrame,true);
        setTitle("���뱸��");       //���ñ���

        JPanel contentPanel=new JPanel(new BorderLayout());     //���������
        this.setContentPane(contentPanel);

        JScrollPane tablePanel=new JScrollPane();       //�������ñ������
        contentPanel.add(tablePanel,BorderLayout.CENTER);   //tablepanel�����м�
        table=pwdBLL.getJTable();                   //��pwdBLL���ȡJTable
        final DefaultTableModel tableModel= (DefaultTableModel) table.getModel();   //��ñ���model

        tablePanel.setViewportView(table);          //������tablepanel

        JPanel controlPanel=new JPanel(new BorderLayout());     //�������ÿ��ư�ť�����
        contentPanel.add(controlPanel,BorderLayout.SOUTH);

        JPanel textPanel=new JPanel();                  //������ʾ�ı�������
        controlPanel.add(textPanel,BorderLayout.NORTH);

        textPanel.add(new JLabel("���: "));
        IDLabel=new JLabel("���");           //���ID��ǩ
        textPanel.add(IDLabel);


        textPanel.add(new JLabel("����: "));      //����ı���
        nameText=new JTextField(10);
        textPanel.add(nameText);

        textPanel.add(new JLabel("����: "));
        pwdText=new JTextField(10);
        textPanel.add(pwdText);

        textPanel.add(new JLabel("��ע: "));
        commentsText=new JTextField(10);
        textPanel.add(commentsText);

        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);  //ѡ��ģʽΪ��ѡ
        table.addMouseListener(new MouseAdapter(){    //����¼�
            public void mouseClicked(MouseEvent e){
                int selectedRow = table.getSelectedRow(); //���ѡ��������
                Object o1 = tableModel.getValueAt(selectedRow, 0);      //���ѡ���еĵ�0��Ԫ��
                Object o2 = tableModel.getValueAt(selectedRow, 1);
                Object o3 = tableModel.getValueAt(selectedRow, 2);
                Object o4 = tableModel.getValueAt(selectedRow, 3);
                IDLabel.setText(o1.toString());  //���ı���ֵ
                nameText.setText(o2.toString());
                pwdText.setText(o3.toString());
                commentsText.setText(o4.toString());
            }
        });

        JPanel buttonPanel=new JPanel();            //��Ӱ�ť
        controlPanel.add(buttonPanel,BorderLayout.SOUTH);

        final JButton addButton = new JButton("���");   //��Ӱ�ť
        addButton.addActionListener(new ActionListener(){//����¼�
            public void actionPerformed(ActionEvent e){   //��Ӽ���

               int resultID=-1;     //resultIDΪ������ݵ�ID
               resultID=pwdBLL.add(nameText.getText(),pwdText.getText(),commentsText.getText());    //�����ݿ�������ݲ����ID
               String []rowValues = {String.valueOf(resultID),nameText.getText(),pwdText.getText(),commentsText.getText()};
               tableModel.addRow(rowValues);  //���һ��
               int rowCount = table.getRowCount() +1;   //��������1
                JOptionPane.showMessageDialog(null,"��ӳɹ�");
            }
        });
        buttonPanel.add(addButton);



        final JButton updateButton = new JButton("�޸�");   //�޸İ�ť
        updateButton.addActionListener(new ActionListener(){//����¼�
            public void actionPerformed(ActionEvent e){
                int selectedRow = table.getSelectedRow();//���ѡ���е�����
                if(selectedRow!= -1)   //�Ƿ����ѡ����
                {
                    //�޸�ָ����ֵ��

                    tableModel.setValueAt(nameText.getText(), selectedRow, 1);
                    tableModel.setValueAt(pwdText.getText(), selectedRow, 2);
                    tableModel.setValueAt(commentsText.getText(), selectedRow, 3);
                }

                pwdBLL.update(IDLabel.getText(),nameText.getText(),pwdText.getText(),commentsText.getText());   //�������ݿ�
                JOptionPane.showMessageDialog(null,"�޸ĳɹ�");
            }
        });
        buttonPanel.add(updateButton);


        final JButton delButton = new JButton("ɾ��");        //ɾ����ť
        delButton.addActionListener(new ActionListener(){//����¼�
            public void actionPerformed(ActionEvent e){
                String ID;
                int selectedRow = table.getSelectedRow();//���ѡ���е�����
                ID=(String)tableModel.getValueAt(selectedRow, 0);
                if(selectedRow!=-1)  //����ѡ����
                {
                    tableModel.removeRow(selectedRow);  //ɾ����
                }
                pwdBLL.delete(ID);
                JOptionPane.showMessageDialog(null,"ɾ���ɹ�");
            }
        });
        buttonPanel.add(delButton);

        final JButton searchButton = new JButton("����");     //������ť
        searchButton.addActionListener(new ActionListener(){//����¼�
            public void actionPerformed(ActionEvent e){
                int selectedItem;
                String searchText;
                new searchDialog(pwdDialog.this,items);     //���������Ի���
                selectedItem=searchDialog.getSelectedItem();        //�����������
                searchText=searchDialog.getSearchText();            //�������������
                highlightRow(selectedItem,searchText);              //������ѯ���������
            }

            private void highlightRow(final int selectedItem, final String searchText) {    //����ָ����

                final DefaultTableCellRenderer tcr = new DefaultTableCellRenderer()
                {

                    public Component getTableCellRendererComponent(JTable table, Object value,
                                                                   boolean isSelected, boolean hasFocus, int row, int column)
                    {
                        if(tableModel.getValueAt(row, selectedItem).toString().equals(searchText)){
                            setBackground(Color.yellow);        //ƥ���б�����Ϊ��ɫ
                        }
                        else
                            setBackground(Color.white);         //������Ϊ��ɫ
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



        setSize(800, 400);          //���öԻ������
        this.setLocationRelativeTo(null);
        setVisible(true);
    }
}
