package Manager;

import java.awt.BorderLayout;
import java.awt.Checkbox;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

import DB.BasketlistDAO;
import DB.BasketlistDTO;
import DB.GoodsDAO;
import member.BasketList;
import member.OrderList;

public class ShoppingMall extends JFrame {
	Scanner in = new Scanner(System.in);
	String header[] = { "상품코드", "상품이름", "수량", "가격", "체크" };
	JTabbedPane tabpane = new JTabbedPane();
	DefaultTableModel tablemodel = new DefaultTableModel(header, 0);
	JTable table = new JTable(tablemodel);
	JScrollPane tableScroll = new JScrollPane(table);

	// center panel
	JPanel tab_center = new JPanel();
	JPanel tab_south = new JPanel();
	JPanel south_north = new JPanel();

	// JTextField[] txtfield = new JTextField[5];
	JTextField tfield = null;

	int modIntRow = -1;
	int chk = 0;
	int num = 0;

	private GoodsDAO gdao = GoodsDAO.getInstance();
	BasketlistDAO dao = BasketlistDAO.getInstance();
	ArrayList<String[]> initList = new ArrayList<>();
	String id = null;
	private BasketlistDTO dto = null;
	ArrayList<String[]> goodsList = new ArrayList<>();
	ArrayList<BasketlistDTO> rlist = new ArrayList<>();
	JTextField total = new JTextField(10);
	
	
	public ShoppingMall(String id) {
		super("쇼핑몰");// super의 생성자 호출
		this.id = id;
		System.out.println("현재 로그인 아이디: "+id);
		Dimension size = new Dimension(600, 400);
		createpanel();
		createtb();
		tablesetting();

		init();
		createchkbox();
		createcombobox();

		TableColumn column = null;
		//changeCellEditor(table, column);

		this.setLocation(300, 300);
		this.setSize(size);
		this.add(tabpane);
		this.setVisible(true);
		this.setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);

	}

//	public void changeCellEditor(JTable table, TableColumn column) {
//		// TableColumn cntcombo = table.getColumnModel().getColumn(2);
//		// int num = 0;
//		// int rowNum = tablemodel.getRowCount();
//
//		table.addMouseListener(new MouseAdapter() {
//			public void mouseClicked(MouseEvent e) {
//				if (e.getButton() == 1) {
//					modIntRow = table.getSelectedRow();
//					int ch = Integer.valueOf((String) table.getValueAt(table.getSelectedRow(), 2));
//					System.out.println(ch);
//					JComboBox box = new JComboBox();
//					int[] narray = null;
//					for (int i = 0; i < initList.size(); i++) {
//						num = Integer.parseInt(initList.get(i)[2]);
//						narray = new int[num];
//						for (int j = 1; j <= narray.length; j++) {
//							if (num == ch) {
//								System.out.println("같다 멈춰야해");
//								box.addItem(j);
//							}
//						}
//						table.getColumn("수량").setCellEditor(new DefaultCellEditor(box));
//					}
//				}
//			}
//		});
//	}

	private void createchkboxt() {

//		JCheckBox box = new JCheckBox();
//		table.getColumn("체크").setCellEditor(new DefaultCellEditor(box));
//		box.setHorizontalAlignment(JLabel.CENTER);

		Container c = getContentPane();
		DefaultTableCellRenderer renderer = new MyCheckbox();
		table.getColumn("체크").setCellRenderer(renderer);
		JCheckBox box = new JCheckBox();
		box.setHorizontalAlignment(JLabel.CENTER);
		table.getColumn("체크").setCellEditor(new DefaultCellEditor(box));

//		for (int i = 0; i < initList.size(); i++) {
//			if (initList.get(i)[4].equals("false")) {
//				Boolean ch = Boolean.valueOf(initList.get(i)[4]);
//				box.setSelected(((Boolean) ch).booleanValue());
//				System.out.println("?????");
//			} else if (initList.get(i)[4].equals("true")) {
//				Boolean ch = Boolean.valueOf(initList.get(i)[4]);
//				box.setSelected(((Boolean) ch).booleanValue());
//				System.out.println("!!!!!");
//			}
//		}
		c.add(box);
	}

	private void createchkbox() {
		Container c = getContentPane();
		JCheckBox box = new JCheckBox();
		// Component comp = this;
		box.setHorizontalAlignment(JLabel.CENTER);
		table.getColumn("체크").setCellEditor(new DefaultCellEditor(box));
		box.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				box.setBorderPainted(true);
				box.setHorizontalAlignment(JLabel.CENTER);
				if (e.getStateChange() == ItemEvent.SELECTED) {
					modIntRow = table.getSelectedRow();
					String in[] = new String[5];
					for (int i = 0; i < table.getColumnCount() - 1; i++) {
						in[i] = (String) table.getValueAt(table.getSelectedRow(), i);
						chk = 1;
					}
					System.out.println("chk: " + chk);
					in[4] = String.valueOf(table.getSelectedRow());

					if (chk == 1) {
						String[] sarray = new String[5];
						for (int i = 0; i < table.getColumnCount(); i++) {
							String g = String.valueOf(table.getValueAt(table.getSelectedRow(), i));
							sarray[i] = g;
						}
						goodsList.add(sarray);
					}
					int sum=0;
					for(int i=0;table.getSelectedRowCount()-1;i++) {
						
						
					}
					
					
//					for (int i = 0; i < in.length; i++) {
//						in[i] = total.getText();
//						total.setText("");
//						int sum = Integer.parseInt(in[2]) * Integer.parseInt(in[3]);
//						total.setText(String.valueOf(sum));
//						total = (JTextField) table.getValueAt(table.getSelectedRow(), sum);
//
//					}
					for (String[] a : goodsList) {
						for (int i = 0; i < a.length; i++) {
							System.out.println("sarray" + "[" + i + "]" + a[i]);
						}

					}
				}
			}
		});
	}

	private void makesDTO(String id, ArrayList<String[]> goodsList, int chk) {
		for (int j = 0; j < goodsList.size(); j++) {
			dto = new BasketlistDTO();
			dto.setId(id);
			int code = Integer.parseInt(goodsList.get(j)[0]);
			dto.setCode(code);
			dto.setCname(goodsList.get(j)[1]);
			int cnt = Integer.parseInt(goodsList.get(j)[2]);
			dto.setCnt(cnt);
			int price = Integer.parseInt(goodsList.get(j)[3]);
			dto.setPrice(price);
			dto.setCheck(chk);
			rlist.add(dto);
		}
		for (int j = 0; j < rlist.size(); j++) {
			System.out.println("----------------------------");
			System.out.println("rlist: " + rlist.get(j).getId());
		}

	}

	private void gotoInsert(BasketlistDTO dto) {
		for (int j = 0; j < rlist.size(); j++) {
			dao.Insert(rlist.get(j));
		}

	}

//	private DefaultTableCellRenderer dcr = new DefaultTableCellRenderer() {
//		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
//				int row, int column) {
//			Component comp = null;
//
//			if (column == 4) {
//				JCheckBox box = new JCheckBox();
//				box.setBorderPainted(true);
//				box.setHorizontalAlignment(JLabel.CENTER);
//				comp = box;
//			}
//			return comp;
//		}
//	};

//	protected void makesDTO(BasketlistDTO) {
//		this.id = id;
//
//	}

	private void saveToDB(String id, ArrayList<String> goodsList, int chk) {
		dto = new BasketlistDTO();
		dto.setId(id);
		int code = Integer.parseInt(goodsList.get(0));
		dto.setCode(code);
		dto.setCname(goodsList.get(1));
		int cnt = Integer.parseInt(goodsList.get(2));
		dto.setCnt(cnt);
		int price = Integer.parseInt(goodsList.get(3));
		dto.setPrice(price);
		dto.setCheck(chk);
		dao.Insert(dto);
	}

	private void init() {
		initList = gdao.getList();
		for (int i = 0; i < initList.size(); i++) {
			tablemodel.addRow(initList.get(i));
		}
		int rowNum = tablemodel.getRowCount();
		int colNum = tablemodel.getColumnCount();
	}

	public void tablesetting() {
		table.setRowMargin(0);
		table.getColumnModel().setColumnMargin(0);
		table.getTableHeader().setReorderingAllowed(false);
		table.getTableHeader().setResizingAllowed(false);

		table.setShowVerticalLines(false);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == 1) {// 마우스 왼쪽 클릭
					modIntRow = table.getSelectedRow();
					// for (int i = 0; i < txtfield.length; i++) {
					// txtfield[i].setText((String) table.getValueAt(modIntRow,
					// i));
					// }
					// modIntRow = table.getSelectedRow();
					// tfield.setText((String) table.getValueAt(modIntRow, 5));
					// }
					if (e.getClickCount() == 2) {// 왼쪽 더블 클릭

					}
					if (e.getClickCount() == 3) {

						modIntRow = table.getSelectedRow();
					}
				}
			}
		});
		DefaultTableCellRenderer ts = new DefaultTableCellRenderer();// 테이블의 셀
																		// 값을 정렬
		ts.setHorizontalAlignment(SwingConstants.CENTER);
		TableColumnModel tc = table.getColumnModel();
		for (int i = 0; i < tc.getColumnCount(); i++) {
			tc.getColumn(i).setCellRenderer(ts);
		}

	}
	private void createcombobox() {
	String[] cnt= {"1","2","3","4","5"};
	
	  JComboBox<String> comboBox = new JComboBox<String>(cnt);
        comboBox.setMaximumRowCount(1);
	TableCellEditor editor = new DefaultCellEditor(comboBox);
	   table.getColumnModel().getColumn(2).setCellEditor(editor);
	   
	   JScrollPane scrollPane = new JScrollPane(table);
        this.add(scrollPane, BorderLayout.CENTER);
        this.setSize(600, 370);
        this.setVisible(true);
}

	private void createtb() {
		// south_north.setLayout(new BoxLayout(south_north, BoxLayout.X_AXIS));
		// for (int i = 0; i < txtfield.length; i++) {
		// south_north.add(txtfield[i] = new JTextField());
		// }
		JLabel all = new JLabel("총 금액");
		south_north.add(all);

		JTextField total = new JTextField(10);
		south_north.add(total);

		JButton addB = new JButton("장바구니에 담기");
		south_north.add(addB);

		addB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String in[] = new String[5];
				tablemodel.addRow(in);
				makesDTO(id, goodsList, chk);
				gotoInsert(dto);
				new BasketList(id);
			}

		});

		JButton modB = new JButton("바로 주문하기");
		south_north.add(modB);
		modB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String in[] = new String[5];
				modIntRow = -1;
				new OrderList(id);
			}
		});

	}

	private void createpanel() {
		this.add(tab_center, "Center");
		this.add(tab_south, "South");

		tab_center.setLayout(new BorderLayout());
		tab_center.add(tableScroll, "Center");
		tab_center.add(tab_south, "South");
		tabpane.add("shopping", tab_center);

		tab_south.setLayout(new BorderLayout());
		tab_south.add(south_north, "North");
		// tab_south.add(south_south, "South");

	}

}
