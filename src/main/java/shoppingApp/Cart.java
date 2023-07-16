package shoppingApp;

import java.util.ArrayList;
import java.util.List;

public class Cart {
	

	int pid;
	String pname;
	String pdesc;
	int pprice ;
	int catid;
	public Cart(int pid, String pname, String pdesc, int pprice, int catid) {
		super();
		this.pid = pid;
		this.pname = pname;
		this.pdesc = pdesc;
		this.pprice = pprice;
		this.catid = catid;
	}
	public Cart() {
		super();
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public String getPdesc() {
		return pdesc;
	}
	public void setPdesc(String pdesc) {
		this.pdesc = pdesc;
	}
	public int getPprice() {
		return pprice;
	}
	public void setPprice(int pprice) {
		this.pprice = pprice;
	}
	public int getCatid() {
		return catid;
	}
	public void setCatid(int catid) {
		this.catid = catid;
	}
	@Override
	public String toString() {
		return "Cart [pid=" + pid + ", pname=" + pname + ", pdesc=" + pdesc + ", pprice=" + pprice + ", catid=" + catid
				+ "]";
	}
	
}
