public class Player {
    private int vida;
    private boolean vivo;

    
    public Player() {
        setVida(6);
        setVivo(true);
    }

    public boolean isVivo() {
        return vivo;
    }

    public void setVivo(boolean vivo) {
        this.vivo = vivo;
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }


}
