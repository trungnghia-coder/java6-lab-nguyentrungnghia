package poly.edu.lab5.database;

import poly.edu.lab5.entity.Student;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


public class Database {
    public static Map<String, Student> map = new HashMap<>();

    static {
        map.put(getKey(), new Student("SV01", "Lý Thái Tổ", true, 9.5));
        map.put(getKey(), new Student("SV02", "Lê Trọng Tấn", true, 4.5));
        map.put(getKey(), new Student("SV03", "Nguyễn Thị Minh Khai", false, 8.5));
        map.put(getKey(), new Student("SV04", "Đoàn Trung Trực", true, 6.0));
    }

    public static String getKey() {
        return Integer.toHexString(UUID.randomUUID().toString().hashCode());
    }
}
