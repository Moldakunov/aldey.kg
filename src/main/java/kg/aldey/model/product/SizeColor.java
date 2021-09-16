package kg.aldey.model.product;

public class SizeColor {
    private String size;
    private String colorCode;
    private String colorName;
    private String colorEnName;

    public SizeColor() {
    }

    public SizeColor(String size, String colorCode, String colorName, String colorEnName) {
        this.size = size;
        this.colorCode = Integer.toHexString(Integer.parseInt(colorCode));
        this.colorName = colorName;
        this.colorEnName = colorEnName;
        System.out.println(colorCode);
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getColorCode() {
        return colorCode;
    }

    public void setColorCode(String colorCode) {
        this.colorCode = colorCode;
    }

    public String getColorName() {
        return colorName;
    }

    public void setColorName(String colorName) {
        this.colorName = colorName;
    }

    public String getColorEnName() {
        return colorEnName;
    }

    public void setColorEnName(String colorEnName) {
        this.colorEnName = colorEnName;
    }

    @Override
    public String toString() {
        return "SizeColor{" +
                "size='" + size + '\'' +
                ", colorCode=" + colorCode +
                ", colorName='" + colorName + '\'' +
                ", colorEnName='" + colorEnName + '\'' +
                '}';
    }
}
