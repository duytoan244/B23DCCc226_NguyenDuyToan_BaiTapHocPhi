import java.util.Scanner;

abstract class SinhVien {
    protected String maSV;
    protected int soTinChi;

    public SinhVien(String maSV, int soTinChi) {
        this.maSV = maSV;
        this.soTinChi = soTinChi;
    }

    public abstract long hocPhi();

    public String getMaSV() {
        return maSV;
    }
}

class SinhVienChinhQuy extends SinhVien {
    public SinhVienChinhQuy(String maSV, int soTinChi) {
        super(maSV, soTinChi);
    }

    @Override
    public long hocPhi() {
        return soTinChi * 450000L;
    }
}

class SinhVienChatLuongCao extends SinhVien {
    public SinhVienChatLuongCao(String maSV, int soTinChi) {
        super(maSV, soTinChi);
    }

    @Override
    public long hocPhi() {
        return (long) (soTinChi * 450000L * 1.5);
    }
}

class SinhVienLienThong extends SinhVien {
    public SinhVienLienThong(String maSV, int soTinChi) {
        super(maSV, soTinChi);
    }

    @Override
    public long hocPhi() {
        return soTinChi * 450000L + 2000000L;
    }
}

public class cau2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        SinhVien[] ds = new SinhVien[n];
        String[] maHe = new String[n];

        for (int i = 0; i < n; i++) {
            String he = sc.next();
            String maSV = sc.next();
            int soTinChi = sc.nextInt();

            maHe[i] = he;

            if (he.equals("CQ")) {
                ds[i] = new SinhVienChinhQuy(maSV, soTinChi);
            } else if (he.equals("CLC")) {
                ds[i] = new SinhVienChatLuongCao(maSV, soTinChi);
            } else {
                ds[i] = new SinhVienLienThong(maSV, soTinChi);
            }
        }

        for (int i = 0; i < n; i++) {
            System.out.println(ds[i].getMaSV() + " " + maHe[i] + " " + ds[i].hocPhi());
        }
    }
}
