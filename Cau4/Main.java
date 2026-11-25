import java.util.LinkedHashMap;
import java.util.Map;
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
        return hocPhi * 7 / 10;
    }
}

class HocBong implements MienGiam {
    @Override
    public long tinhGiam(long hocPhi) {
        return Math.min(hocPhi, 3_000_000L);
    }
}

abstract class SinhVien {
    private static final long DON_GIA = 450_000;
    private final String ma;
    private final String he;
    private final int tinChi;
    private MienGiam chinhSach;
    SinhVien(String ma, String he, int tinChi) {
        this.ma = ma;
        this.he = he;
        this.tinChi = tinChi;
    }

    String getMa() {
        return ma;
    }

    String getHe() {
        return he;
    }
    long hocPhiCoBan() {
        return tinChi * DON_GIA;
    }

    abstract long hocPhi();

    void setChinhSach(MienGiam chinhSach) {
        this.chinhSach = chinhSach;
    }

    long tienGiam() {
        return chinhSach == null ? 0 : chinhSach.tinhGiam(hocPhi());
    }

    long phaiNop() {
        return hocPhi() - tienGiam();
    }

    static SinhVien tao(String he, String ma, int tinChi) {
        switch (he) {
            case "CQ": return new SinhVienCQ(ma, tinChi);
            case "CLC": return new SinhVienCLC(ma, tinChi);
            case "LT": return new SinhVienLT(ma, tinChi);
            default: throw new IllegalArgumentException("Ma he khong hop le");
        }
    }
    @Override
    public String toString() {
        return ma + " " + he + " " + hocPhi() + " " + tienGiam() + " " + phaiNop();
    }
}

class SinhVienCQ extends SinhVien {
    SinhVienCQ(String ma, int tinChi) {
        super(ma, "CQ", tinChi);
    }

    @Override
    long hocPhi() {
        return hocPhiCoBan();
    }
}

class SinhVienCLC extends SinhVien {
    SinhVienCLC(String ma, int tinChi) {
        super(ma, "CLC", tinChi);
    }

    @Override
    long hocPhi() {
        return hocPhiCoBan() * 3 / 2;
    }
}

class SinhVienLT extends SinhVien {
    SinhVienLT(String ma, int tinChi) {
        super(ma, "LT", tinChi);
    }

    @Override
    long hocPhi() {
        return hocPhiCoBan() + 2_000_000;
    }
}

public class Main {
    private static MienGiam taoChinhSach(String maChinhSach) {
        switch (maChinhSach) {
            case "CS": return new ConChinhSach();
            case "HN": return new HoNgheo();
            case "HB": return new HocBong();
            default: throw new IllegalArgumentException("Ma chinh sach khong hop le");
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Map<String, SinhVien> danhSach = new LinkedHashMap<>();
        int n = sc.nextInt();

        for (int i = 0; i < n; i++) {
            String he = sc.next();
            String ma = sc.next();
            int tinChi = sc.nextInt();
            danhSach.put(ma, SinhVien.tao(he, ma, tinChi));
        }

        int m = sc.nextInt();
        for (int i = 0; i < m; i++) {
            String ma = sc.next();
            String maChinhSach = sc.next();
            SinhVien sinhVien = danhSach.get(ma);
            if (sinhVien == null) {
                System.out.println("NOT FOUND " + ma);
            } else {
                sinhVien.setChinhSach(taoChinhSach(maChinhSach));
            }
        }

        for (SinhVien sinhVien : danhSach.values()) {
            System.out.println(sinhVien);
        }

        String[] cacHe = {"CQ", "CLC", "LT"};
        for (String he : cacHe) {
            int soSinhVien = 0;
            long tongPhaiNop = 0;
            for (SinhVien sinhVien : danhSach.values()) {
                if (he.equals(sinhVien.getHe())) {
                    soSinhVien++;
                    tongPhaiNop += sinhVien.phaiNop();
                }
            }

            if (soSinhVien > 0) {
                System.out.println(he + " " + soSinhVien + " " + tongPhaiNop);
            }
        }
    }
}
