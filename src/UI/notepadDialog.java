package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Vector;
import javax.swing.*;
import javax.swing.table.*;
import BLL.*;
/**
 * Created by Administrator on 2014/6/15.
 */
public class notepadDialog extends JDialog{

    private static JTable table;
    private static JLabel IDLabel;
    private static JTextField dateText;
    private static JTextField sortText;
    private static JTextField captionText;
    private static JTextField commentsText;
    String[] items={"����","���","����","��ע"};


    public notepadDialog(MainFrame mainFrame) {
        super(mainFrame,true);
        setTitle("�ճ�����");

        JPanel contentPanel=new JPanel(new BorderLayout());
        this.setContentPane(contentPanel);

        JScrollPane tablePanel=new JScrollPane();
        contentPanel.add(tablePanel,BorderLayout.CENTER);
        table=notepadBLL.getJTable();
        final DefaultTableModel tableModel= (DefaultTableModel) table.getModel();
        tablePanel.setViewportView(table);


        JPanel controlPanel=new JPanel(new BorderLayout());
        contentPanel.add(controlPanel,BorderLayout.SOUTH);


        JPanel textPanel=new JPanel();
        controlPanel.add(textPanel,BorderLayout.NORTH);

        textPanel.add(new JLabel("���: "));  //����ı���

        IDLabel=new JLabel("���");
        textPanel.add(IDLabel);


        textPanel.add(new JLabel("����: "));
        dateText=new JTextField(10);
        textPanel.add(dateText);

        textPanel.add(new JLabel("���: "));
        sortText=new JTextField(10);
        textPanel.add(sortText);

        textPanel.add(new JLabel("����: "));
        captionText=new JTextField(10);
        textPanel.add(captionText);

        textPanel.add(new JLabel("��ע: "));
        commentsText=new JTextField(10);
        textPanel.add(commentsText);



        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);  //��ѡ
        table.addMouseListener(new MouseAdapter(){    //����¼�
            public void mouseClicked(MouseEvent e){
                int selectedRow = table.getSelectedRow(); //���ѡ��������
                Object o1 = tableModel.getValueAt(selectedRow, 0);

                Object o2 = tableModel.getValueAt(selectedRow, 1);
                Object o3 = tableModel.getValueAt(selectedRow, 2);
                Object o4 = tableModel.getValueAt(selectedRow, 3);
                Object o5 = tableModel.getValueAt(selectedRow, 4);
                IDLabel.setText(o1.toString());  //���ı���ֵ
                dateText.setText(o2.toString());
                sortText.setText(o3.toString());
                captionText.setText(o4.toString());
                commentsText.setText(o5.toString());
            }
        });

        JPanel buttonPanel=new JPanel();            //��Ӱ�ť
        controlPanel.add(buttonPanel,BorderLayout.SOUTH);

        final JButton addButton = new JButton("���");   //��Ӱ�ť
        addButton.addActionListener(new ActionListener(){//����¼�
            public void actionPerformed(ActionEvent e){

                int resultID=-1;
                resultID=notepadBLL.add(dateText.getText(),sortText.getText(),captionText.getText(),commentsText.getText());
                String []rowValues = {String.valueOf(resultID),dateText.getText(),sortText.getText(),captionText.getText(),commentsText.getText()};

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

                    tableModel.setValueAt(dateText.getText(), selectedRow, 1);
                    tableModel.setValueAt(sortText.getText(), selectedRow, 2);
                    tableModel.setValueAt(captionText.getText(), selectedRow, 3);
                    tableModel.setValueAt(commentsText.getText(), selectedRow, 4);
                }

                notepadBLL.update(IDLabel.getText(),dateText.getText(),sortText.getText(),captionText.getText(),commentsText.getText());
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

                notepadBLL.delete(ID);
                JOptionPane.showMessageDialog(null,"ɾ���ɹ�");
            }
        });
        buttonPanel.add(delButton);

        final JButton searchButton = new JButton("����");
        searchButton.addActionListener(new ActionListener(){//����¼�
            public void actionPerformed(ActionEvent e){
                int selectedItem;
                String searchText;
                new searchDialog(notepadDialog.this,items);
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
