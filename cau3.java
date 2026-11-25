import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

class DuLieuKhongHopLeException extends Exception {
    public DuLieuKhongHopLeException(String message) {
        super(message);
    }
}

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

class QuanLyHocPhi {
    private ArrayList<SinhVien> danhSach = new ArrayList<>();
    private Set<String> maDaCo = new HashSet<>();

    public void themSinhVien(String maHe, String maSV, String chuoiTinChi)
            throws DuLieuKhongHopLeException {

        if (!maHe.equals("CQ") && !maHe.equals("CLC") && !maHe.equals("LT")) {
            throw new DuLieuKhongHopLeException("Ma he khong hop le");
        }

        int soTinChi;
        try {
            soTinChi = Integer.parseInt(chuoiTinChi);
        } catch (NumberFormatException e) {
            throw new DuLieuKhongHopLeException("So tin chi khong phai so nguyen");
        }

        if (soTinChi < 1 || soTinChi > 30) {
            throw new DuLieuKhongHopLeException("So tin chi ngoai pham vi");
        }

        if (maDaCo.contains(maSV)) {
            throw new DuLieuKhongHopLeException("Ma sinh vien bi trung");
        }

        SinhVien sv;
        if (maHe.equals("CQ")) {
            sv = new SinhVienChinhQuy(maSV, soTinChi);
        } else if (maHe.equals("CLC")) {
            sv = new SinhVienChatLuongCao(maSV, soTinChi);
        } else {
            sv = new SinhVienLienThong(maSV, soTinChi);
        }

        danhSach.add(sv);
        maDaCo.add(maSV);
    }

    public void inDanhSach() {
        Collections.sort(danhSach, new Comparator<SinhVien>() {
            @Override
            public int compare(SinhVien a, SinhVien b) {
                int cmp = Long.compare(b.hocPhi(), a.hocPhi());
                if (cmp != 0) return cmp;
                return a.getMaSV().compareTo(b.getMaSV());
            }
        });

        long tong = 0;
        for (SinhVien sv : danhSach) {
            String maHe;
            if (sv instanceof SinhVienChinhQuy) {
                maHe = "CQ";
            } else if (sv instanceof SinhVienChatLuongCao) {
                maHe = "CLC";
            } else {
                maHe = "LT";
            }

            System.out.println(sv.getMaSV() + " " + maHe + " " + sv.hocPhi());
            tong += sv.hocPhi();
        }

        System.out.println("TONG " + tong);
    }
}

public class cau3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine().trim());

        QuanLyHocPhi ql = new QuanLyHocPhi();
        ArrayList<String> loi = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String dong = sc.nextLine().trim();
            String[] p = dong.split("\\s+");

            String maHe = p[0];
            String maSV = p[1];
            String soTinChi = p[2];

            try {
                ql.themSinhVien(maHe, maSV, soTinChi);
            } catch (DuLieuKhongHopLeException e) {
                loi.add("INVALID " + maSV);
            }
        }

        for (String s : loi) {
            System.out.println(s);
        }

        ql.inDanhSach();
    }
}