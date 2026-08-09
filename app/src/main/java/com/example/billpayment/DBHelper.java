package com.example.billpayment;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "billpayment.db";
    public static final int DB_VERSION = 1;

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // ================= USERS =================
        db.execSQL(
                "CREATE TABLE users (" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "phone TEXT," +
                        "email TEXT," +
                        "is_allowed INTEGER)"
        );

        // ================= BILLS =================
        db.execSQL(
                "CREATE TABLE bills (" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "user_id INTEGER," +
                        "bill_type TEXT," +
                        "bill_code TEXT," +
                        "payment_code TEXT," +
                        "amount INTEGER," +
                        "is_paid INTEGER)"
        );

        // ================= PAYMENTS =================
        db.execSQL(
                "CREATE TABLE payments (" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "user_id INTEGER," +
                        "bill_id INTEGER," +
                        "amount INTEGER," +
                        "status TEXT," +
                        "date TEXT)"
        );

        insertSeedUsers(db);
        insertSeedBills(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Not needed for university project
    }

    // ================= SEED USERS - 20 phone + 20 email + 5 blocked =================
    private void insertSeedUsers(SQLiteDatabase db) {

        // -------- 20 PHONE USERS --------
        db.execSQL("INSERT INTO users VALUES (null,'09100000001',null,1)");
        db.execSQL("INSERT INTO users VALUES (null,'09100000002',null,1)");
        db.execSQL("INSERT INTO users VALUES (null,'09100000003',null,1)");
        db.execSQL("INSERT INTO users VALUES (null,'09100000004',null,1)");
        db.execSQL("INSERT INTO users VALUES (null,'09100000005',null,1)");
        db.execSQL("INSERT INTO users VALUES (null,'09100000006',null,1)");
        db.execSQL("INSERT INTO users VALUES (null,'09100000007',null,1)");
        db.execSQL("INSERT INTO users VALUES (null,'09100000008',null,1)");
        db.execSQL("INSERT INTO users VALUES (null,'09100000009',null,1)");
        db.execSQL("INSERT INTO users VALUES (null,'09100000010',null,1)");
        db.execSQL("INSERT INTO users VALUES (null,'09100000011',null,1)");
        db.execSQL("INSERT INTO users VALUES (null,'09100000012',null,1)");
        db.execSQL("INSERT INTO users VALUES (null,'09100000013',null,1)");
        db.execSQL("INSERT INTO users VALUES (null,'09100000014',null,1)");
        db.execSQL("INSERT INTO users VALUES (null,'09100000015',null,1)");
        db.execSQL("INSERT INTO users VALUES (null,'09100000016',null,1)");
        db.execSQL("INSERT INTO users VALUES (null,'09100000017',null,1)");
        db.execSQL("INSERT INTO users VALUES (null,'09100000018',null,1)");
        db.execSQL("INSERT INTO users VALUES (null,'09100000019',null,1)");
        db.execSQL("INSERT INTO users VALUES (null,'09100000020',null,1)");

        // -------- 20 EMAIL USERS --------
        db.execSQL("INSERT INTO users VALUES (null,null,'user1@gmail.com',1)");
        db.execSQL("INSERT INTO users VALUES (null,null,'user2@gmail.com',1)");
        db.execSQL("INSERT INTO users VALUES (null,null,'user3@gmail.com',1)");
        db.execSQL("INSERT INTO users VALUES (null,null,'user4@gmail.com',1)");
        db.execSQL("INSERT INTO users VALUES (null,null,'user5@gmail.com',1)");
        db.execSQL("INSERT INTO users VALUES (null,null,'user6@gmail.com',1)");
        db.execSQL("INSERT INTO users VALUES (null,null,'user7@gmail.com',1)");
        db.execSQL("INSERT INTO users VALUES (null,null,'user8@gmail.com',1)");
        db.execSQL("INSERT INTO users VALUES (null,null,'user9@gmail.com',1)");
        db.execSQL("INSERT INTO users VALUES (null,null,'user10@gmail.com',1)");
        db.execSQL("INSERT INTO users VALUES (null,null,'user11@gmail.com',1)");
        db.execSQL("INSERT INTO users VALUES (null,null,'user12@gmail.com',1)");
        db.execSQL("INSERT INTO users VALUES (null,null,'user13@gmail.com',1)");
        db.execSQL("INSERT INTO users VALUES (null,null,'user14@gmail.com',1)");
        db.execSQL("INSERT INTO users VALUES (null,null,'user15@gmail.com',1)");
        db.execSQL("INSERT INTO users VALUES (null,null,'user16@gmail.com',1)");
        db.execSQL("INSERT INTO users VALUES (null,null,'user17@gmail.com',1)");
        db.execSQL("INSERT INTO users VALUES (null,null,'user18@gmail.com',1)");
        db.execSQL("INSERT INTO users VALUES (null,null,'user19@gmail.com',1)");
        db.execSQL("INSERT INTO users VALUES (null,null,'user20@gmail.com',1)");

        // -------- 5 BLOCKED USERS --------
        db.execSQL("INSERT INTO users VALUES (null,'09900000001',null,0)");
        db.execSQL("INSERT INTO users VALUES (null,'09900000002',null,0)");
        db.execSQL("INSERT INTO users VALUES (null,null,'blocked1@gmail.com',0)");
        db.execSQL("INSERT INTO users VALUES (null,null,'blocked2@gmail.com',0)");
        db.execSQL("INSERT INTO users VALUES (null,null,'blocked3@gmail.com',0)");
    }


    private void insertSeedBills(SQLiteDatabase db) {

        // -------- USER 1 --------
        db.execSQL("INSERT INTO bills VALUES (null,1,'برق','E1001','P1001',120000,0)");
        db.execSQL("INSERT INTO bills VALUES (null,1,'آب','W1001','P1002',45000,0)");
        db.execSQL("INSERT INTO bills VALUES (null,1,'گاز','G1001','P1003',78000,0)");
        db.execSQL("INSERT INTO bills VALUES (null,1,'تلفن','T1001','P1004',23000,0)");
        db.execSQL("INSERT INTO bills VALUES (null,1,'اینترنت','I1001','P1005',54000,0)");

        // -------- USER 2 --------
        db.execSQL("INSERT INTO bills VALUES (null,2,'برق','E2001','P2001',99000,0)");
        db.execSQL("INSERT INTO bills VALUES (null,2,'آب','W2001','P2002',38000,0)");
        db.execSQL("INSERT INTO bills VALUES (null,2,'گاز','G2001','P2003',66000,0)");
        db.execSQL("INSERT INTO bills VALUES (null,2,'تلفن','T2001','P2004',18000,0)");
        db.execSQL("INSERT INTO bills VALUES (null,2,'اینترنت','I2001','P2005',47000,0)");

        // -------- USER 3 --------
        db.execSQL("INSERT INTO bills VALUES (null,3,'برق','E3001','P3001',110000,0)");
        db.execSQL("INSERT INTO bills VALUES (null,3,'آب','W3001','P3002',42000,0)");
        db.execSQL("INSERT INTO bills VALUES (null,3,'گاز','G3001','P3003',71000,0)");
        db.execSQL("INSERT INTO bills VALUES (null,3,'تلفن','T3001','P3004',26000,0)");
        db.execSQL("INSERT INTO bills VALUES (null,3,'اینترنت','I3001','P3005',58000,0)");

// -------- USER 4 --------
        db.execSQL("INSERT INTO bills VALUES (null,4,'برق','E4001','P4001',98000,0)");
        db.execSQL("INSERT INTO bills VALUES (null,4,'آب','W4001','P4002',39000,0)");
        db.execSQL("INSERT INTO bills VALUES (null,4,'گاز','G4001','P4003',65000,0)");
        db.execSQL("INSERT INTO bills VALUES (null,4,'تلفن','T4001','P4004',21000,0)");
        db.execSQL("INSERT INTO bills VALUES (null,4,'اینترنت','I4001','P4005',46000,0)");

// -------- USER 5 --------
        db.execSQL("INSERT INTO bills VALUES (null,5,'برق','E5001','P5001',125000,0)");
        db.execSQL("INSERT INTO bills VALUES (null,5,'آب','W5001','P5002',48000,0)");
        db.execSQL("INSERT INTO bills VALUES (null,5,'گاز','G5001','P5003',82000,0)");
        db.execSQL("INSERT INTO bills VALUES (null,5,'تلفن','T5001','P5004',27000,0)");
        db.execSQL("INSERT INTO bills VALUES (null,5,'اینترنت','I5001','P5005',60000,0)");

// -------- USER 6 --------
        db.execSQL("INSERT INTO bills VALUES (null,6,'برق','E6001','P6001',105000,0)");
        db.execSQL("INSERT INTO bills VALUES (null,6,'آب','W6001','P6002',41000,0)");
        db.execSQL("INSERT INTO bills VALUES (null,6,'گاز','G6001','P6003',73000,0)");
        db.execSQL("INSERT INTO bills VALUES (null,6,'تلفن','T6001','P6004',24000,0)");
        db.execSQL("INSERT INTO bills VALUES (null,6,'اینترنت','I6001','P6005',52000,0)");

// -------- USER 7 --------
        db.execSQL("INSERT INTO bills VALUES (null,7,'برق','E7001','P7001',118000,0)");
        db.execSQL("INSERT INTO bills VALUES (null,7,'آب','W7001','P7002',46000,0)");
        db.execSQL("INSERT INTO bills VALUES (null,7,'گاز','G7001','P7003',79000,0)");
        db.execSQL("INSERT INTO bills VALUES (null,7,'تلفن','T7001','P7004',29000,0)");
        db.execSQL("INSERT INTO bills VALUES (null,7,'اینترنت','I7001','P7005',61000,0)");

// -------- USER 8 --------
        db.execSQL("INSERT INTO bills VALUES (null,8,'برق','E8001','P8001',93000,0)");
        db.execSQL("INSERT INTO bills VALUES (null,8,'آب','W8001','P8002',36000,0)");
        db.execSQL("INSERT INTO bills VALUES (null,8,'گاز','G8001','P8003',62000,0)");
        db.execSQL("INSERT INTO bills VALUES (null,8,'تلفن','T8001','P8004',20000,0)");
        db.execSQL("INSERT INTO bills VALUES (null,8,'اینترنت','I8001','P8005',45000,0)");

// -------- USER 9 --------
        db.execSQL("INSERT INTO bills VALUES (null,9,'برق','E9001','P9001',132000,0)");
        db.execSQL("INSERT INTO bills VALUES (null,9,'آب','W9001','P9002',50000,0)");
        db.execSQL("INSERT INTO bills VALUES (null,9,'گاز','G9001','P9003',86000,0)");
        db.execSQL("INSERT INTO bills VALUES (null,9,'تلفن','T9001','P9004',30000,0)");
        db.execSQL("INSERT INTO bills VALUES (null,9,'اینترنت','I9001','P9005',64000,0)");

// -------- USER 10 --------
        db.execSQL("INSERT INTO bills VALUES (null,10,'برق','E10001','P10001',97000,0)");
        db.execSQL("INSERT INTO bills VALUES (null,10,'آب','W10001','P10002',40000,0)");
        db.execSQL("INSERT INTO bills VALUES (null,10,'گاز','G10001','P10003',68000,0)");
        db.execSQL("INSERT INTO bills VALUES (null,10,'تلفن','T10001','P10004',22000,0)");
        db.execSQL("INSERT INTO bills VALUES (null,10,'اینترنت','I10001','P10005',49000,0)");

        // -------- USER 11 --------
        db.execSQL("INSERT INTO bills VALUES (null,11,'برق','E11001','P11001',101000,0)");
        db.execSQL("INSERT INTO bills VALUES (null,11,'آب','W11001','P11002',39000,0)");
        db.execSQL("INSERT INTO bills VALUES (null,11,'گاز','G11001','P11003',70000,0)");
        db.execSQL("INSERT INTO bills VALUES (null,11,'تلفن','T11001','P11004',25000,0)");
        db.execSQL("INSERT INTO bills VALUES (null,11,'اینترنت','I11001','P11005',53000,0)");

// -------- USER 12 --------
        db.execSQL("INSERT INTO bills VALUES (null,12,'برق','E12001','P12001',115000,0)");
        db.execSQL("INSERT INTO bills VALUES (null,12,'آب','W12001','P12002',47000,0)");
        db.execSQL("INSERT INTO bills VALUES (null,12,'گاز','G12001','P12003',81000,0)");
        db.execSQL("INSERT INTO bills VALUES (null,12,'تلفن','T12001','P12004',28000,0)");
        db.execSQL("INSERT INTO bills VALUES (null,12,'اینترنت','I12001','P12005',60000,0)");

// -------- USER 13 --------
        db.execSQL("INSERT INTO bills VALUES (null,13,'برق','E13001','P13001',95000,0)");
        db.execSQL("INSERT INTO bills VALUES (null,13,'آب','W13001','P13002',36000,0)");
        db.execSQL("INSERT INTO bills VALUES (null,13,'گاز','G13001','P13003',63000,0)");
        db.execSQL("INSERT INTO bills VALUES (null,13,'تلفن','T13001','P13004',21000,0)");
        db.execSQL("INSERT INTO bills VALUES (null,13,'اینترنت','I13001','P13005',48000,0)");

// -------- USER 14 --------
        db.execSQL("INSERT INTO bills VALUES (null,14,'برق','E14001','P14001',123000,0)");
        db.execSQL("INSERT INTO bills VALUES (null,14,'آب','W14001','P14002',51000,0)");
        db.execSQL("INSERT INTO bills VALUES (null,14,'گاز','G14001','P14003',88000,0)");
        db.execSQL("INSERT INTO bills VALUES (null,14,'تلفن','T14001','P14004',31000,0)");
        db.execSQL("INSERT INTO bills VALUES (null,14,'اینترنت','I14001','P14005',65000,0)");

// -------- USER 15 --------
        db.execSQL("INSERT INTO bills VALUES (null,15,'برق','E15001','P15001',108000,0)");
        db.execSQL("INSERT INTO bills VALUES (null,15,'آب','W15001','P15002',43000,0)");
        db.execSQL("INSERT INTO bills VALUES (null,15,'گاز','G15001','P15003',75000,0)");
        db.execSQL("INSERT INTO bills VALUES (null,15,'تلفن','T15001','P15004',26000,0)");
        db.execSQL("INSERT INTO bills VALUES (null,15,'اینترنت','I15001','P15005',56000,0)");

// -------- USER 16 --------
        db.execSQL("INSERT INTO bills VALUES (null,16,'برق','E16001','P16001',92000,0)");
        db.execSQL("INSERT INTO bills VALUES (null,16,'آب','W16001','P16002',35000,0)");
        db.execSQL("INSERT INTO bills VALUES (null,16,'گاز','G16001','P16003',61000,0)");
        db.execSQL("INSERT INTO bills VALUES (null,16,'تلفن','T16001','P16004',19000,0)");
        db.execSQL("INSERT INTO bills VALUES (null,16,'اینترنت','I16001','P16005',45000,0)");

// -------- USER 17 --------
        db.execSQL("INSERT INTO bills VALUES (null,17,'برق','E17001','P17001',130000,0)");
        db.execSQL("INSERT INTO bills VALUES (null,17,'آب','W17001','P17002',52000,0)");
        db.execSQL("INSERT INTO bills VALUES (null,17,'گاز','G17001','P17003',90000,0)");
        db.execSQL("INSERT INTO bills VALUES (null,17,'تلفن','T17001','P17004',33000,0)");
        db.execSQL("INSERT INTO bills VALUES (null,17,'اینترنت','I17001','P17005',68000,0)");

// -------- USER 18 --------
        db.execSQL("INSERT INTO bills VALUES (null,18,'برق','E18001','P18001',99000,0)");
        db.execSQL("INSERT INTO bills VALUES (null,18,'آب','W18001','P18002',40000,0)");
        db.execSQL("INSERT INTO bills VALUES (null,18,'گاز','G18001','P18003',69000,0)");
        db.execSQL("INSERT INTO bills VALUES (null,18,'تلفن','T18001','P18004',23000,0)");
        db.execSQL("INSERT INTO bills VALUES (null,18,'اینترنت','I18001','P18005',50000,0)");

// -------- USER 19 --------
        db.execSQL("INSERT INTO bills VALUES (null,19,'برق','E19001','P19001',117000,0)");
        db.execSQL("INSERT INTO bills VALUES (null,19,'آب','W19001','P19002',46000,0)");
        db.execSQL("INSERT INTO bills VALUES (null,19,'گاز','G19001','P19003',80000,0)");
        db.execSQL("INSERT INTO bills VALUES (null,19,'تلفن','T19001','P19004',29000,0)");
        db.execSQL("INSERT INTO bills VALUES (null,19,'اینترنت','I19001','P19005',62000,0)");

// -------- USER 20 --------
        db.execSQL("INSERT INTO bills VALUES (null,20,'برق','E20001','P20001',135000,0)");
        db.execSQL("INSERT INTO bills VALUES (null,20,'آب','W20001','P20002',54000,0)");
        db.execSQL("INSERT INTO bills VALUES (null,20,'گاز','G20001','P20003',92000,0)");
        db.execSQL("INSERT INTO bills VALUES (null,20,'تلفن','T20001','P20004',34000,0)");
        db.execSQL("INSERT INTO bills VALUES (null,20,'اینترنت','I20001','P20005',70000,0)");

    }

    // ================= LOGIN CHECK =================

    public int checkUserAccess(String input) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(
                "SELECT id,is_allowed FROM users WHERE phone=? OR email=?",
                new String[]{input, input}
        );

        if (cursor.moveToFirst()) {
            int allowed = cursor.getInt(cursor.getColumnIndexOrThrow("is_allowed"));
            cursor.close();
            return allowed; // 1 allowed, 0 blocked
        }

        cursor.close();
        return -1; // user not found
    }

    // ================= GET USER ID =================
    public int getUserId(String input) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(
                "SELECT id FROM users WHERE phone=? OR email=?",
                new String[]{input, input}
        );

        if (cursor.moveToFirst()) {
            int id = cursor.getInt(0);
            cursor.close();
            return id;
        }

        cursor.close();
        return -1;
    }
    public Cursor getBillsForUser(int userId) {

        SQLiteDatabase db = this.getReadableDatabase();

        return db.rawQuery(
                "SELECT * FROM bills WHERE user_id=? AND is_paid=0",
                new String[]{String.valueOf(userId)}
        );
    }

    // گرفتن اطلاعات قبض
    public Cursor getBillById(int billId) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery(
                "SELECT * FROM bills WHERE id=?",
                new String[]{String.valueOf(billId)}
        );
    }

    // ثبت پرداخت
    public void insertPayment(int userId, int billId, int amount, String status, String date) {

        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL(
                "INSERT INTO payments (user_id,bill_id,amount,status,date) VALUES (?,?,?,?,?)",
                new Object[]{userId, billId, amount, status, date}
        );
    }

    // آپدیت قبض به پرداخت‌شده
    public void markBillAsPaid(int billId) {

        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL(
                "UPDATE bills SET is_paid=1 WHERE id=?",
                new Object[]{billId}
        );
    }

    public Cursor getPaymentsForUser(int userId) {

        SQLiteDatabase db = this.getReadableDatabase();

        return db.rawQuery(
                "SELECT p.amount, p.status, p.date, b.bill_type " +
                        "FROM payments p " +
                        "JOIN bills b ON p.bill_id = b.id " +
                        "WHERE p.user_id=? " +
                        "ORDER BY p.id DESC",
                new String[]{String.valueOf(userId)}
        );
    }
    public Cursor getUserById(int userId) {

        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery(
                "SELECT * FROM users WHERE id=?",
                new String[]{String.valueOf(userId)}
        );
    }

    public int getBillsCount(int userId) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(
                "SELECT COUNT(*) FROM bills WHERE user_id=?",
                new String[]{String.valueOf(userId)}
        );

        c.moveToFirst();
        int count = c.getInt(0);
        c.close();
        return count;
    }

    public int getPaymentsCount(int userId) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(
                "SELECT COUNT(*) FROM payments WHERE user_id=?",
                new String[]{String.valueOf(userId)}
        );

        c.moveToFirst();
        int count = c.getInt(0);
        c.close();
        return count;
    }

// SUM OF PAYMENTS

    public int getTotalPaidAmount(int userId) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(
                "SELECT IFNULL(SUM(amount),0) FROM payments WHERE user_id=? AND status='success'",
                new String[]{String.valueOf(userId)}
        );

        c.moveToFirst();
        int sum = c.getInt(0);
        c.close();
        return sum;
    }


// UNPAID BILLS COUNT
    public int getUnpaidBillsCount(int userId) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(
                "SELECT COUNT(*) FROM bills WHERE user_id=? AND is_paid=0",
                new String[]{String.valueOf(userId)}
        );

        c.moveToFirst();
        int count = c.getInt(0);
        c.close();
        return count;
    }


// UNPAID BILLS TOTAL AMOUNT (OPTIONAL)

    public int getUnpaidBillsAmount(int userId) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(
                "SELECT IFNULL(SUM(amount),0) FROM bills WHERE user_id=? AND is_paid=0",
                new String[]{String.valueOf(userId)}
        );

        c.moveToFirst();
        int sum = c.getInt(0);
        c.close();
        return sum;
    }

}
