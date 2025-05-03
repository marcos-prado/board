package br.com.dio.ui;

import static br.com.dio.persistence.config.ConnectionConfig.getConnection;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import br.com.dio.persistence.entity.BoardColumnsEntity;
import br.com.dio.persistence.entity.BoardColumnsKindEnum;
import br.com.dio.persistence.entity.BoardEntity;
import br.com.dio.service.BoardQueryService;
import br.com.dio.service.BoardService;


public class MainMenu{
    private final Scanner scanner = new Scanner(System.in);

    public void execute() throws SQLException{
        System.out.println("Bem vindo ao gerenciador de boards, escolaha a opção desejada");
        var option = -1;
        while (true) {
            System.out.println("1 - Criar um novo board");
            System.out.println("2 - Selecionar um board existente");
            System.out.println("3 - Excluir um board");
            System.out.println("4 - Sair");
            option = scanner.nextInt();
            switch (option) {
                case 1 -> createBoard();
                case 2 -> selectBoard();
                case 3 -> deleteBoard();
                case 4 -> System.exit(0);
                default -> System.out.println("Opção inválida, escolha uma opção do menu");

            }

        }
    }

    private void createBoard() throws SQLException {
        var entity = new BoardEntity();
        System.out.println("Informe o nome do seu board");
        entity.setName(scanner.next());

        System.out.println("Seu board terá colunas além das 3 opções? Se sim informe quantas, senão digite '0' ");
        var addtionalColumns = scanner.nextInt();

        List<BoardColumnsEntity> columns = new ArrayList<>();

        System.out.println("Informe o nome da coluna inicial do board");
        var initialColumnName = scanner.next();
        var initialColumn = createColumn(initialColumnName, BoardColumnsKindEnum.INITIAL, 0);
        columns.add(initialColumn);

        for (int i = 0; i < addtionalColumns; i++) {
            System.out.println("Informe o nome da coluna  de tarefa pendente");
            var pendingColumnName = scanner.next();
            var pendingColumn = createColumn(initialColumnName, BoardColumnsKindEnum.PENDING, i + 1);
            columns.add(pendingColumn);
        }

        System.out.println("Informe o nome da coluna final");
        var finalColumnName = scanner.next();
        var finalColumn = createColumn(finalColumnName, BoardColumnsKindEnum.PENDING, addtionalColumns + 1);
        columns.add(finalColumn);

        System.out.println("Informe o nome da coluna de cancelamento do board");
        var cancelColumnName = scanner.next();
        var cancelColumn = createColumn(finalColumnName, BoardColumnsKindEnum.CANCEL, addtionalColumns + 2);
        columns.add(finalColumn);

        entity.setBoardColumns(columns);
        try (var connection = getConnection()) {
            var service = new BoardService(connection);
            service.insert(entity);
        }

    }

    private void selectBoard() throws SQLException {
        System.out.println("Informe o id do board que deseja selecionar");
        var id = scanner.nextLong();
        try(var connection = getConnection()){
            var queryService = new BoardQueryService(connection);
            var optional = queryService.findById(id);
            optional.ifPresentOrElse(
                b -> new BoardMenu(b).execute(),
                ()-> System.out.printf("Não foi encontrado um board com o id %s\n", id)
            );
            
        }

    }

    private void deleteBoard() throws SQLException {
        System.out.println("Informe o id do board que deseja excluir");
        var id = scanner.nextLong();
        try (var connection = getConnection()) {
            var service = new BoardService(connection);
            if (service.delete(id)) {
                System.out.printf("O board %s foi excluido\n", id);
            } else {
                System.out.printf("Não foi encontrado um board com id %s\n", id);
            }

        }
    }

    private BoardColumnsEntity createColumn(final String name, final BoardColumnsKindEnum kind, final int order){
        var boardColumn = new BoardColumnsEntity();
        boardColumn.setName(name);
        boardColumn.setKind(kind);
        boardColumn.setOrder(order);
        return boardColumn;
    }

}
