package com.gatedInc.game.view;

import com.gatedInc.game.graphic.Animation;
import com.gatedInc.game.graphic.Sprite;
import com.gatedInc.game.model.CharactersAttributes.Health;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class HealthView {

    Health health;

    private Image[] zeroHP;
    private Image[] oneHP;
    private Image[] twoHP;
    private Image[] threeHP;
    private Image[] fourHP;
    private Image[] fiveHP;
    private Image[] sixHP;

    private Animation zero;
    private Animation one;
    private Animation two;
    private Animation three;
    private Animation four;
    private Animation five;
    private Animation six;
    private Animation currentAnimation;

    public HealthView(Health health) {
        this.health = health;
        setAnimationImages();
        setAnimation();
        currentAnimation.start();

    }

    private void setAnimationImages() {
        Sprite.resetSpriteLoader();
        zeroHP = new Image[]{Sprite.getSprite(0, 0, "HealthBar/ZERO_HP"), Sprite.getSprite(0, 1, "HealthBar/ZERO_HP"), Sprite.getSprite(0, 2, "HealthBar/ZERO_HP"), Sprite.getSprite(0, 3, "HealthBar/ZERO_HP"), Sprite.getSprite(0, 4, "HealthBar/ZERO_HP"), Sprite.getSprite(0, 5, "HealthBar/ZERO_HP")};
        Sprite.resetSpriteLoader();
        oneHP = new Image[]{Sprite.getSprite(0, 0, "HealthBar/ONE_HP"), Sprite.getSprite(0, 1, "HealthBar/ONE_HP"), Sprite.getSprite(0, 2, "HealthBar/ONE_HP"), Sprite.getSprite(0, 3, "HealthBar/ONE_HP"), Sprite.getSprite(0, 4, "HealthBar/ONE_HP"), Sprite.getSprite(0, 5, "HealthBar/ONE_HP")};
        Sprite.resetSpriteLoader();
        twoHP = new Image[]{Sprite.getSprite(0, 0, "HealthBar/TWO_HP"), Sprite.getSprite(0, 1, "HealthBar/TWO_HP"), Sprite.getSprite(0, 2, "HealthBar/TWO_HP"), Sprite.getSprite(0, 3, "HealthBar/TWO_HP"), Sprite.getSprite(0, 4, "HealthBar/TWO_HP"), Sprite.getSprite(0, 5, "HealthBar/TWO_HP")};
        Sprite.resetSpriteLoader();
        threeHP = new Image[]{Sprite.getSprite(0, 0, "HealthBar/THREE_HP"), Sprite.getSprite(0, 1, "HealthBar/THREE_HP"), Sprite.getSprite(0, 2, "HealthBar/THREE_HP"), Sprite.getSprite(0, 3, "HealthBar/THREE_HP"), Sprite.getSprite(0, 4, "HealthBar/THREE_HP"), Sprite.getSprite(0, 5, "HealthBar/THREE_HP")};
        Sprite.resetSpriteLoader();
        fourHP = new Image[]{Sprite.getSprite(0, 0, "HealthBar/FOUR_HP"), Sprite.getSprite(0, 1, "HealthBar/FOUR_HP"), Sprite.getSprite(0, 2, "HealthBar/FOUR_HP"), Sprite.getSprite(0, 3, "HealthBar/FOUR_HP"), Sprite.getSprite(0, 4, "HealthBar/FOUR_HP"), Sprite.getSprite(0, 5, "HealthBar/FOUR_HP")};
        Sprite.resetSpriteLoader();
        fiveHP = new Image[]{Sprite.getSprite(0, 0, "HealthBar/FIVE_HP"), Sprite.getSprite(0, 1, "HealthBar/FIVE_HP"), Sprite.getSprite(0, 2, "HealthBar/FIVE_HP"), Sprite.getSprite(0, 3, "HealthBar/FIVE_HP"), Sprite.getSprite(0, 4, "HealthBar/FIVE_HP"), Sprite.getSprite(0, 5, "HealthBar/FIVE_HP")};
        Sprite.resetSpriteLoader();
        sixHP = new Image[]{Sprite.getSprite(0, 0, "HealthBar/SIX_HP"), Sprite.getSprite(0, 1, "HealthBar/SIX_HP"), Sprite.getSprite(0, 2, "HealthBar/SIX_HP"), Sprite.getSprite(0, 3, "HealthBar/SIX_HP"), Sprite.getSprite(0, 4, "HealthBar/SIX_HP"), Sprite.getSprite(0, 5, "HealthBar/SIX_HP")};
    }

    private void setAnimation() {
        zero = new Animation(zeroHP, 10);
        one = new Animation(oneHP, 10);
        two = new Animation(twoHP, 10);
        three = new Animation(threeHP, 10);
        four = new Animation(fourHP, 10);
        five = new Animation(fiveHP, 10);
        six = new Animation(sixHP, 10);

        currentAnimation = six;
    }

    private void setHealthBar() {
        switch (health.getCurrentHealth()) {
            case 0:
                currentAnimation = zero;
                currentAnimation.start();
                break;
            case 1:
                currentAnimation = one;
                currentAnimation.start();
                break;
            case 2:
                currentAnimation = two;
                currentAnimation.start();
                break;
            case 3:
                currentAnimation = three;
                currentAnimation.start();
                break;
            case 4:
                currentAnimation = four;
                currentAnimation.start();
                break;
            case 5:
                currentAnimation = five;
                currentAnimation.start();
                break;
            case 6:
                currentAnimation = six;
                currentAnimation.start();
                break;
        }
    }

    public void render(GraphicsContext context) {
        context.drawImage(currentAnimation.getSprite(), 10, 10, 180, 180);
    }

    public void update() {
        setHealthBar();
        currentAnimation.update(-1);
    }

}