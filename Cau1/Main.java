import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.Locale;
import java.util.Scanner;
import java.util.StringJoiner;

class SinhVien {
    private final String ma;
    private final String hoTen;
    private final String ngaySinh;

    SinhVien(String ma, String hoTen, String ngaySinh) {
        this.ma = ma.trim();
        this.hoTen = chuanHoaTen(hoTen);
        this.ngaySinh = chuanHoaNgay(ngaySinh);
    }

    private static String chuanHoaTen(String ten) {
        StringJoiner ketQua = new StringJoiner(" ");
        for (String tu : ten.toLowerCase(Locale.ROOT).split("(?U)\\s+")) {
            if (!tu.isEmpty()) {
                int viTri = tu.offsetByCodePoints(0, 1);
                ketQua.add(tu.substring(0, viTri).toUpperCase(Locale.ROOT)
                        + tu.substring(viTri));
            }
        }
        return ketQua.toString();
    }

    private static String chuanHoaNgay(String ngay) {
        String[] phan = ngay.trim().split("/");
        return String.format(Locale.ROOT, "%02d/%02d/%s",
                Integer.parseInt(phan[0]), Integer.parseInt(phan[1]), phan[2]);
    }

    @Override
    public String toString() {
        return ma + " " + hoTen + " " + ngaySinh;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in, "UTF-8");
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out, StandardCharsets.UTF_8));
        int n = Integer.parseInt(sc.nextLine().trim());

        for (int i = 0; i < n; i++) {
            String ma = sc.nextLine();
            String hoTen = sc.nextLine();
            String ngaySinh = sc.nextLine();
            out.println(new SinhVien(ma, hoTen, ngaySinh));
        }
        out.flush();
    }
}
