class Biblioteca {
    private Lista cancionesFavoritas;
    private Lista playlists;

    public Biblioteca() {
        this.cancionesFavoritas = new Lista();
        this.playlists = new Lista();
    }

    public void anadirAFavoritos(Cancion cancion) {
        if (!cancionEsFavorita(cancion)) {
            cancion.favorita();
            cancionesFavoritas.insertEnd(cancion);
            System.out.println("Canción añadida a favoritos.");
        } else {
            System.out.println("La canción ya está en favoritos.");
        }
    }

    public void eliminarDeFavoritos(Cancion cancion) {
        if (cancionEsFavorita(cancion)) {
            cancion.favorita();
            Lista nuevaLista = new Lista();
            Node current = cancionesFavoritas.getFirst();
            while (current != null) {
                if (!current.getValue().equals(cancion)) {
                    nuevaLista.insertEnd(current.getValue());
                }
                current = current.getNext();
            }
            cancionesFavoritas = nuevaLista;
            System.out.println("Canción eliminada de favoritos.");
        } else {
            System.out.println("La canción no está en favoritos.");
        }
    }

    public void verCancionesFavoritas() {
        System.out.println("=== Canciones Favoritas ===");
        System.out.println(cancionesFavoritas.listAll());
    }

    public void crearPlaylist(String titulo) {
        Playlist nuevaPlaylist = new Playlist(titulo);
        playlists.insertEnd(nuevaPlaylist);
        System.out.println("Nueva playlist '" + titulo + "' creada.");
    }

    public void eliminarPlaylist(int indice) {
        Lista nuevaLista = new Lista();
        int count = 0;
        Node iterator = playlists.getFirst();

        while (iterator != null) {
            if (count != indice) {
                nuevaLista.insertEnd(iterator.getValue());
            }
            count++;
            iterator = iterator.getNext();
        }
        playlists = nuevaLista;
        System.out.println("Playlist eliminada.");
    }

    public void anadirCancionAPlaylist(int indicePlaylist, Cancion cancion) {
        Node iterator = playlists.getFirst();
        int count = 0;

        while (iterator != null) {
            if (count == indicePlaylist) {
                Playlist playlist = (Playlist) iterator.getValue();
                playlist.anadirCancion(cancion);
                System.out.println("Canción añadida a la playlist '" + playlist.getTitulo() + "'.");
                return;
            }
            count++;
            iterator = iterator.getNext();
        }
        System.out.println("Índice de playlist inválido.");
    }

    public void eliminarCancionDePlaylist(int indicePlaylist, int indiceCancion) {
        Node iterator = playlists.getFirst();
        int count = 0;

        while (iterator != null) {
            if (count == indicePlaylist) {
                Playlist playlist = (Playlist) iterator.getValue();
                playlist.eliminarCancion(indiceCancion);
                return;
            }
            count++;
            iterator = iterator.getNext();
        }
        
        System.out.println("Índice de playlist inválido.");
    }

    public void verPlaylists() {
        System.out.println("=== Playlists ===");
        int count = 0;
        Node iterator = playlists.getFirst();

        while (iterator != null) {
            Playlist playlist = (Playlist) iterator.getValue();
            System.out.println(count + ". " + playlist.getTitulo() + ":");
            System.out.println(playlist.getCanciones().listAll());
            iterator = iterator.getNext();
            count++;
        }
    }

    public Playlist obtenerPlaylist(int indice) {
        Node iterator = playlists.getFirst();
        int count = 0;
        while (iterator != null) {
            if (count == indice) {
                return (Playlist) iterator.getValue();
            }
            count++;
            iterator = iterator.getNext();
        }
        return null;
    }

    public Playlist seleccionarPlaylistParaReproduccion(int indice) {
        return obtenerPlaylist(indice);
    }

    private boolean cancionEsFavorita(Cancion cancion) {
        Node iterator = cancionesFavoritas.getFirst();
        while (iterator != null) {
            if (iterator.getValue().equals(cancion)) {
                return true;
            }
            iterator = iterator.getNext();
        }
        return false;
    }
}