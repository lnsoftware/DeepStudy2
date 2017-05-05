package sdwvit.avatars;

public class AvatarCreatorClassBuilder {
    private int imageCount = 1;
    private int baseSize = 5;
    private double fillFactor = 0.7;
    private float alphaFactor = 0.5f;
    private int pixelSize = baseSize/5;
    private int borderSize = pixelSize / 2;
    private int imageSize = baseSize + (borderSize * 2);

    public AvatarCreatorClassBuilder withImageCount(int imageCount) {
        this.imageCount = imageCount;
        return this;
    }

    public AvatarCreatorClassBuilder withFillFactor(double fillFactor) {
        this.fillFactor = fillFactor;
        return this;
    }

    public AvatarCreatorClassBuilder withAlphaFactor(float alphaFactor) {
        this.alphaFactor = alphaFactor;
        return this;
    }

    public AvatarCreatorClassBuilder withBaseSize(int baseSize) {
        this.baseSize = baseSize;
        updateVars();
        return this;
    }

    public AvatarCreatorClassBuilder withPixelSize(int pixelSize) {
        this.pixelSize = pixelSize;
        updateVars();
        return this;
    }

    public AvatarCreatorClassBuilder whichNeedsBorder(boolean needBorder) {
        if (!needBorder) {
            borderSize = 0;
            imageSize = baseSize;
        }
        return this;
    }

    public AvatarCreatorClassBuilder withImageCount(String imageCount) {
        return withImageCount(Integer.parseInt(imageCount));
    }

    public AvatarCreatorClassBuilder withFillFactor(String fillFactor) {
        return withFillFactor(Double.parseDouble(fillFactor));
    }

    public AvatarCreatorClassBuilder withAlphaFactor(String alphaFactor) {
        return withAlphaFactor(Float.parseFloat(alphaFactor));
    }

    public AvatarCreatorClassBuilder withBaseSize(String baseSize) {
        return withBaseSize(Integer.parseInt(baseSize));
    }

    public AvatarCreatorClassBuilder withPixelSize(String pixelSize) {
        return withPixelSize(Integer.parseInt(pixelSize));
    }

    public AvatarCreatorClassBuilder whichNeedsBorder(String needBorder) {
        return whichNeedsBorder(Boolean.parseBoolean(needBorder));
    }

    public AvatarCreatorImpl build() {
        return new AvatarCreatorImpl(
                imageCount, baseSize, fillFactor, alphaFactor, pixelSize, borderSize, imageSize);
    }

    private void updateVars() {
        borderSize = pixelSize / 2;
        imageSize = baseSize + (borderSize * 2);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else {
            if (o instanceof AvatarCreatorClassBuilder) {
                AvatarCreatorClassBuilder casted = (AvatarCreatorClassBuilder)o;
                if (imageCount == casted.imageCount
                    && baseSize == casted.baseSize
                    && fillFactor == casted.fillFactor
                    && alphaFactor == casted.alphaFactor
                    && pixelSize == casted.pixelSize
                    && borderSize == casted.borderSize
                    && imageSize == casted.imageSize) {
                    return true;
                }
            }
            return false;
        }
    }
}
