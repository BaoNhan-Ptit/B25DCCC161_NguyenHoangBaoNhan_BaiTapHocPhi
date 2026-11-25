import java.util.Scanner;

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

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        for (int i = 0; i < n; i++) {
            String he = sc.next();
            String ma = sc.next();
            int tinChi = sc.nextInt();
            System.out.println(SinhVien.tao(he, ma, tinChi));
        }
    }
}
