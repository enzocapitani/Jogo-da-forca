import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Principal extends JFrame {

        ImageIcon iconeForca1 = new ImageIcon(
                        "C:/Users/mauri/OneDrive/Área de Trabalho/Projetos/Jogo da forca/jogo da forca/src/sprites/forca1.png");
        ImageIcon iconeForca2 = new ImageIcon(
                        "C:/Users/mauri/OneDrive/Área de Trabalho/Projetos/Jogo da forca/jogo da forca/src/sprites/forca2.png");
        ImageIcon iconeForca3 = new ImageIcon(
                        "C:/Users/mauri/OneDrive/Área de Trabalho/Projetos/Jogo da forca/jogo da forca/src/sprites/forca3.png");
        ImageIcon iconeForca4 = new ImageIcon(
                        "C:/Users/mauri/OneDrive/Área de Trabalho/Projetos/Jogo da forca/jogo da forca/src/sprites/forca4.png");
        ImageIcon iconeForca5 = new ImageIcon(
                        "C:/Users/mauri/OneDrive/Área de Trabalho/Projetos/Jogo da forca/jogo da forca/src/sprites/forca5.png");
        ImageIcon iconeForca6 = new ImageIcon(
                        "C:/Users/mauri/OneDrive/Área de Trabalho/Projetos/Jogo da forca/jogo da forca/src/sprites/forca6.png");
        ImageIcon iconeForca7 = new ImageIcon(
                        "C:/Users/mauri/OneDrive/Área de Trabalho/Projetos/Jogo da forca/jogo da forca/src/sprites/forca7.png");
        ImageIcon iconeForca8 = new ImageIcon(
                        "C:/Users/mauri/OneDrive/Área de Trabalho/Projetos/Jogo da forca/jogo da forca/src/sprites/forca8.png");

        JLabel imagem = new JLabel(iconeForca1);

        Player p = new Player();

        JLabel labelVida;

        String[] palavras = {
                        "melao", "melancia", "banana", "uva", "manga", "ameixa", "abacaxi", "pitaya", "acerola",
                        "goiaba", "maracuja", "tangerina", "kiwi", "laranja"
        };
        String palavraEscolhida = null;

        JButton botaoEnviar = new JButton("Enviar");

        Random rand = new Random();

        JTextField tentativaTxt = new JTextField(10);

        JLabel palavraTotal = new JLabel();

        public Principal() {

                labelVida = new JLabel(String.valueOf("Vida: " + p.getVida()));
                labelVida.setFont(new Font("Arial", Font.BOLD, 20));

                setSize(500, 500);
                setTitle("Jogo da Forca");
                setDefaultCloseOperation(EXIT_ON_CLOSE);
                setLayout(new BorderLayout());
                setLocationRelativeTo(null);

                estilizacao();

                gerarPalavra();

                add(painelSuperior(), BorderLayout.NORTH);
                add(painelCentral(), BorderLayout.CENTER);
                add(painelInferior(), BorderLayout.SOUTH);

                setVisible(true);
        }

        public JPanel painelSuperior() {
                JPanel painel = new JPanel(new FlowLayout(FlowLayout.LEFT));

                painel.add(labelVida);

                return painel;
        }

        public JPanel painelCentral() {
                JPanel painel = new JPanel();

                painel.add(imagem);

                return painel;
        }

        public JPanel painelInferior() {
                JPanel painel = new JPanel(new GridLayout(2, 1));

                StringBuilder sb = new StringBuilder();

                JPanel painelAdvinha = new JPanel();

                for (int i = 0; i < palavraEscolhida.length() * 2; i++) {
                        if (i % 2 == 0) {
                                sb.append("_");
                        } else {
                                sb.append(" ");
                        }
                }

                palavraTotal.setText(sb.toString());

                painelAdvinha.add(palavraTotal);

                JPanel painelLetras = new JPanel();

                botaoEnviar.addActionListener(e -> {

                        boolean tentativaFalha = true;
                        if (tentativaTxt.getText().trim().equals("")) {
                                JOptionPane.showMessageDialog(this, "Digite uma letra");
                                tentativaFalha = false;
                        } else {
                                for (int i = 0; i < palavraEscolhida.length(); i++) {
                                        if (tentativaTxt.getText().charAt(0) == palavraEscolhida.charAt(i)) {
                                                alterarPalavra();
                                                verificarVitoria();
                                                tentativaFalha = false;
                                                break;
                                        }
                                }
                        }
                        if (tentativaFalha == true) {
                                p.setVida(p.getVida() - 1);
                                labelVida.setText(String.valueOf("Vida: " + p.getVida()));
                                if (p.getVida() < 1) {
                                        mudarImagem();
                                        perderJogo();
                                }
                                mudarImagem();
                        }

                        tentativaTxt.setText("");

                });

                painelLetras.add(tentativaTxt);
                painelLetras.add(botaoEnviar);

                painel.add(painelAdvinha);
                painel.add(painelLetras);
                return painel;
        }

        public void mudarImagem() {
                switch (p.getVida()) {
                        case 6:
                                imagem.setIcon(iconeForca1);
                                break;
                        case 5:
                                imagem.setIcon(iconeForca2);
                                break;
                        case 4:
                                imagem.setIcon(iconeForca3);
                                break;
                        case 3:
                                imagem.setIcon(iconeForca4);
                                break;
                        case 2:
                                imagem.setIcon(iconeForca5);
                                break;
                        case 1:
                                imagem.setIcon(iconeForca6);
                                break;
                        case 0:
                                imagem.setIcon(iconeForca8);
                                break;
                }
        }

        public void perderJogo() {
                JOptionPane.showMessageDialog(this, "Você perdeu!");
                resetarJogo();
        }

        public void gerarPalavra() {
                int num = rand.nextInt(14);

                palavraEscolhida = palavras[num];

                // System.out.println(palavraEscolhida);
        }

        public void alterarPalavra() {

                StringBuilder sb = new StringBuilder();
                int contador = 0;

                for (int i = 0; i < palavraEscolhida.length() * 2; i++) {
                        if (i % 2 == 0) {
                                // se texto da tentativa for igual ao caractere da palavra escolhida no indice
                                // contador
                                if (tentativaTxt.getText().charAt(0) == palavraEscolhida.charAt(contador)) {
                                        if (palavraTotal.getText().charAt(i) == '_') {
                                                sb.append(tentativaTxt.getText().charAt(0));
                                        } else {
                                                sb.append(palavraEscolhida.charAt(contador));
                                        }
                                        contador++;
                                } else {
                                        sb.append(palavraTotal.getText().charAt(i));
                                        contador++;
                                }
                        } else {
                                sb.append(" ");
                        }
                }

                palavraTotal.setText(sb.toString());

        }

        public void verificarVitoria() {
                StringBuilder sb = new StringBuilder();

                for (int i = 0; i < palavraEscolhida.length() * 2; i++) {
                        if (i % 2 == 0) {
                                sb.append(palavraTotal.getText().charAt(i));
                        }
                }

                if (sb.toString().equals(palavraEscolhida)) {
                        JOptionPane.showMessageDialog(this,
                                        "Você ganhou o jogo, a palavra era : " + sb.toString());
                        resetarJogo();
                }
        }

        public void resetarJogo() {
                gerarPalavra();
                p.setVida(6);
                labelVida.setText("Vida: " + p.getVida());

                StringBuilder sb = new StringBuilder();

                for (int i = 0; i < palavraEscolhida.length() * 2; i++) {
                        if (i % 2 == 0) {
                                sb.append("_");
                        } else {
                                sb.append(" ");
                        }
                }

                palavraTotal.setText(sb.toString());

                imagem.setIcon(iconeForca1);
        }

        public void estilizacao() {
                palavraTotal.setFont(new Font("Arial", Font.BOLD, 25));
        }

        public static void main(String[] args) {
                new Principal();

        }
}
