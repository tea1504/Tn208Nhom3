package view.taikhoangiangvien;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.*;

import DAO.GiangVienDAO;
import bean.GiangVien;

@SuppressWarnings("serial")
public class Table extends JPanel {
	private GiangVienSetTableModel model = new GiangVienSetTableModel();
	private JTable table;
	private final ThongTin tt;
	private JScrollPane pane;
	private boolean ok = true;

	public Table(ThongTin _tt) {
		tt = _tt;
		table = new JTable(model);
		table.changeSelection(0, 0, false, false);
		table.setFont(new Font("Tahoma", Font.PLAIN, 18));
		table.getTableHeader().setFont(new Font("Tahoma", Font.PLAIN, 18));
		table.setRowHeight(30);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		pane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridx = 0;
		gbc.gridx = 0;
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		add(pane, gbc);
		table.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				if (ok) {
					int r = table.getSelectedRow();
					tt.txtMaGiangVien.setText(table.getValueAt(r, 0).toString());
					tt.txtTenGiangVien.setText(table.getValueAt(r, 1).toString());
					String ma = table.getValueAt(r, 0).toString();
					GiangVienDAO gvdao = new GiangVienDAO();
					ArrayList<GiangVien> list = gvdao.getGiangVien();
					int index = -1;
					for (int i = 0; i < list.size(); i++) {
						if (list.get(i).getMaGiangVien().compareTo(ma) == 0)
							index = i;
					}
					if(Integer.parseInt(table.getValueAt(r,2).toString())==0) {
						tt.cboQuyenSD.setSelectedIndex(1);
					}else {
						tt.cboQuyenSD.setSelectedIndex(0);
					}
					
				}
			}
		});
	}

	public GiangVienSetTableModel getModel() {
		return model;
	}

	public void setModel(GiangVienSetTableModel model) {
		this.model = model;
	}

	public JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		this.table = table;
	}

	public JScrollPane getPane() {
		return pane;
	}

	public void setPane(JScrollPane pane) {
		this.pane = pane;
	}

	public boolean isOk() {
		return ok;
	}

	public void setOk(boolean ok) {
		this.ok = ok;
	}
	public void DKKT() {
		table.setEnabled(false);
		ok = false;
	}
	public void DKKBT() {
		table.setEnabled(true);
		ok = true;
	}

}
