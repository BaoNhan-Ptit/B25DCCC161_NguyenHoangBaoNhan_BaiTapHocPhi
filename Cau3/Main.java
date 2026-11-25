import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

abstract class SinhVien {
    private static final long DON_GIA = 450_000;
    private final String ma;
    private final String he;
    private final int tinChi;
    SinhVien(String ma, String he, int tinChi) {
        this.ma = ma;
        this.he = he;
        this.tinChi = tinChi;
    }

    String getMa() {
        return ma;
    }
    long hocPhiCoBan() {
        return tinChi * DON_GIA;
    }

    abstract long hocPhi();
    @Override
    public String toString() {
        return ma + " " + he + " " + hocPhi();
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

class DuLieuKhongHopLeException extends Exception {
    DuLieuKhongHopLeException(String ma) {
        super(ma);
    }
}

class QuanLyHocPhi {
    private final ArrayList<SinhVien> danhSach = new ArrayList<>();
    private final Set<String> maDaThem = new HashSet<>();

    void them(String he, String ma, String soTinChi)
            throws DuLieuKhongHopLeException {
        int tinChi;
        try {
            tinChi = Integer.parseInt(soTinChi);
        } catch (NumberFormatException e) {
            throw new DuLieuKhongHopLeException(ma);
        }

        if (tinChi < 1 || tinChi > 30 || maDaThem.contains(ma)) {
            throw new DuLieuKhongHopLeException(ma);
        }

        SinhVien sinhVien;
        switch (he) {
            case "CQ":
                sinhVien = new SinhVienCQ(ma, tinChi);
                break;
            case "CLC":
                sinhVien = new SinhVienCLC(ma, tinChi);
                break;
            case "LT":
                sinhVien = new SinhVienLT(ma, tinChi);
                break;
            default:
                throw new DuLieuKhongHopLeException(ma);
        }

        danhSach.add(sinhVien);
        maDaThem.add(ma);
    }

    void inHocPhi() {
        danhSach.sort(Comparator.comparingLong(SinhVien::hocPhi)
                .reversed()
                .thenComparing(SinhVien::getMa));

        long tong = 0;
        for (SinhVien sinhVien : danhSach) {
            System.out.println(sinhVien);
            tong += sinhVien.hocPhi();
        }
        System.out.println("TONG " + tong);
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        QuanLyHocPhi quanLy = new QuanLyHocPhi();

        for (int i = 0; i < n; i++) {
            String he = sc.next();
            String ma = sc.next();
            String tinChi = sc.next();
            try {
                quanLy.them(he, ma, tinChi);
            } catch (DuLieuKhongHopLeException e) {
                System.out.println("INVALID " + e.getMessage());
            }
        }

        quanLy.inHocPhi();
    }
}
