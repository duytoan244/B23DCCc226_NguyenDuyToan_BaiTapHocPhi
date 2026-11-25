import java.util.ArrayList;
import java.util.Scanner;

interface MienGiam {
    long tinhGiam(long hocPhi);
}

class ConChinhSach implements MienGiam {
    @Override
    public long tinhGiam(long hocPhi) {
        return hocPhi / 2;
    }
}

class HoNgheo implements MienGiam {
    @Override
    public long tinhGiam(long hocPhi) {
        return hocPhi * 70 / 100;
    }
}

class HocBong implements MienGiam {
    @Override
    public long tinhGiam(long hocPhi) {
        return Math.min(3000000L, hocPhi);
    }
}

abstract class SinhVien {
    protected String maSV;
    protected int soTinChi;
    protected MienGiam mienGiam;

    public SinhVien(String maSV, int soTinChi) {
        this.maSV = maSV;
        this.soTinChi = soTinChi;
        this.mienGiam = null;
    }

    public abstract long hocPhi();

    public void setMienGiam(MienGiam mienGiam) {
        this.mienGiam = mienGiam;
    }

    public long tienDuocGiam() {
        if (mienGiam == null) return 0;
        return mienGiam.tinhGiam(hocPhi());
    }

    public long phaiNop() {
        return hocPhi() - tienDuocGiam();
    }

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

public class cau4 {
    private static SinhVien timSinhVien(ArrayList<SinhVien> ds, String maSV) {
        for (SinhVien sv : ds) {
            if (sv.getMaSV().equals(maSV)) return sv;
        }
        return null;
    }

    private static MienGiam taoChinhSach(String ma) {
        if (ma.equals("CS")) return new ConChinhSach();
        if (ma.equals("HN")) return new HoNgheo();
        if (ma.equals("HB")) return new HocBong();
        return null;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        ArrayList<SinhVien> ds = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String maHe = sc.next();
            String maSV = sc.next();
            int soTinChi = sc.nextInt();

            SinhVien sv;
            if (maHe.equals("CQ")) {
                sv = new SinhVienChinhQuy(maSV, soTinChi);
            } else if (maHe.equals("CLC")) {
                sv = new SinhVienChatLuongCao(maSV, soTinChi);
            } else {
                sv = new SinhVienLienThong(maSV, soTinChi);
            }
            ds.add(sv);
        }

        int m = sc.nextInt();

        for (int i = 0; i < m; i++) {
            String maSV = sc.next();
            String maChinhSach = sc.next();

            SinhVien sv = timSinhVien(ds, maSV);

            if (sv == null) {
                System.out.println("NOT FOUND " + maSV);
            } else {
                sv.setMienGiam(taoChinhSach(maChinhSach));
            }
        }

        long tongCQ = 0, tongCLC = 0, tongLT = 0;
        int demCQ = 0, demCLC = 0, demLT = 0;

        for (SinhVien sv : ds) {
            String maHe;

            if (sv instanceof SinhVienChinhQuy) {
                maHe = "CQ";
                demCQ++;
                tongCQ += sv.phaiNop();
            } else if (sv instanceof SinhVienChatLuongCao) {
                maHe = "CLC";
                demCLC++;
                tongCLC += sv.phaiNop();
            } else {
                maHe = "LT";
                demLT++;
                tongLT += sv.phaiNop();
            }

            System.out.println(sv.getMaSV() + " " + maHe + " "
                    + sv.hocPhi() + " " + sv.tienDuocGiam() + " " + sv.phaiNop());
        }

        if (demCQ > 0) System.out.println("CQ " + demCQ + " " + tongCQ);
        if (demCLC > 0) System.out.println("CLC " + demCLC + " " + tongCLC);
        if (demLT > 0) System.out.println("LT " + demLT + " " + tongLT);
    }
}
