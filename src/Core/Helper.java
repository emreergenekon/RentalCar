package Core;

import javax.swing.*;
import java.awt.*;

public class Helper {

    public static void setTheme() {
        for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
            if ("Nimbus".equals(info.getName())) {
                try {
                    UIManager.setLookAndFeel(info.getClassName());
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                break;
            }
        }
    }

    public static void showMessage(String message) {
        optionPaneTR();
        String str;
        String title = switch (message) {
            case "fill" -> {
                str = "Lütfen tüm alanları doldurunuz";
                yield "Hata";
            }
            case "done" -> {
                str = "İşlem başarılı";
                yield "Sonuç";
            }
            case "notFound" -> {
                str = "Kayıt bulunamadı";
                yield "Uyarı";
            }
            case "error" -> {
                str = "Hatalı işlem yaptınız";
                yield "Hata";
            }
            default -> {
                str = message;
                yield "Mesaj";
            }
        };

        JOptionPane.showMessageDialog(null, str, title, JOptionPane.INFORMATION_MESSAGE);

    }

    public static boolean confirm(String str){
        optionPaneTR();
        String msg;
        if (str.equals("sure")){
            msg = "Bu işlemi yapmak istediğine emin misin ?";
        } else {
            msg = str;
        }
        return JOptionPane.showConfirmDialog(null,msg,"Emin misin?",JOptionPane.YES_NO_CANCEL_OPTION) == 0;
    }

    public static boolean isFieldEmpty(JTextField field) {
        return field.getText().trim().isEmpty();
    }

    public static boolean isFieldListEmpty(JTextField[] fieldlist) {
        for (JTextField field : fieldlist) {
            if (isFieldEmpty(field)) {
                return true;
            }
        }
        return false;
    }

    public static int getLocationPoint(String type, Dimension size) {
        return switch (type) {
            case "x" -> (Toolkit.getDefaultToolkit().getScreenSize().width - size.getSize().width) / 2;
            case "y" -> (Toolkit.getDefaultToolkit().getScreenSize().height - size.getSize().height) / 2;
            default -> 0;
        };
    }

    public static void optionPaneTR(){
        UIManager.put("OptionPane.okButtonText","Tamam");
        UIManager.put("OptionPane.yesButtonText","Evet");
        UIManager.put("OptionPane.noButtonText","Hayır");
    }
}