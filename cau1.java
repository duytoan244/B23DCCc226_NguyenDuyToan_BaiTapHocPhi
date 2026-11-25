import java.util.Scanner;

class SinhVien {
    private String maSV;
    private String hoTen;
    private String ngaySinh;

    public SinhVien(String maSV, String hoTen, String ngaySinh) {
        this.maSV = maSV;
        this.hoTen = chuanHoaHoTen(hoTen);
        this.ngaySinh = chuanHoaNgaySinh(ngaySinh);
    }

    private String chuanHoaHoTen(String s) {
        String[] tu = s.trim().toLowerCase().split("\\s+");
        StringBuilder sb = new StringBuilder();

        for (String x : tu) {
            if (x.length() > 0) {
                if (sb.length() > 0) sb.append(" ");
                sb.append(Character.toUpperCase(x.charAt(0)));
                if (x.length() > 1) sb.append(x.substring(1));
            }
        }
        return sb.toString();
    }

    private String chuanHoaNgaySinh(String s) {
        String[] p = s.trim().split("/");
        int ngay = Integer.parseInt(p[0]);
        int thang = Integer.parseInt(p[1]);
        int nam = Integer.parseInt(p[2]);

        return String.format("%02d/%02d/%04d", ngay, thang, nam);
    }

    public void hienThi() {
        System.out.println(maSV + " " + hoTen + " " + ngaySinh);
    }
}

public class cau1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine().trim());

        SinhVien[] ds = new SinhVien[n];

        for (int i = 0; i < n; i++) {
            String maSV = sc.nextLine().trim();
            String hoTen = sc.nextLine();
            String ngaySinh = sc.nextLine().trim();

            ds[i] = new SinhVien(maSV, hoTen, ngaySinh);
        }

        for (SinhVien sv : ds) {
            sv.hienThi();
        }
    }
}
