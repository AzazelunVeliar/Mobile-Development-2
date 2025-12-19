Отчёт по практической работе №6.


fragmentapp

В данном модуле было реализовано простое приложение с использованием одного фрагмента.
Фрагмент TodoFragment отображается внутри MainActivity и содержит список задач, реализованный с помощью RecyclerView.

    public class TodoFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_todo, container, false);
    }

Также в этом модуле реализована передача данных из Activity во Fragment.
Номер студента по списку (№26) передаётся через Bundle при создании фрагмента и используется внутри TodoFragment для отображения информации на экране.


            Bundle args = new Bundle();
            args.putInt("my_number_student", 26);

            TodoFragment fragment = new TodoFragment();
            fragment.setArguments(args);

            getSupportFragmentManager()
                    .beginTransaction()
                    .setReorderingAllowed(true)
                    .add(R.id.fragment_container_view, fragment)
                    .commit();
Получение номера студента во фрагменте (TodoFragment):

    Bundle args = getArguments();
    if (args != null) {
        int number = args.getInt("student_number");
        textView.setText("Студент №" + number);
    }
    
<img width="423" height="945" alt="fragmentapp" src="https://github.com/user-attachments/assets/3a86119d-e6ec-4672-9c17-940510d5ff75" />

fragmentmanagerapp

В данном модуле было реализовано приложение с несколькими фрагментами и использованием FragmentManager.

Экран приложения разделён на два фрагмента:
 1. фрагмент со списком стран (CountryListFragment);
 2. фрагмент с детальной информацией о выбранной стране (DetailsFragment).

Для передачи данных между фрагментами был реализован SharedViewModel, общий для Activity. В нём хранится выбранное пользователем название страны в виде LiveData.

Во фрагменте CountryListFragment был реализован список стран с помощью RecyclerView. При нажатии на элемент списка название страны передаётся в SharedViewModel.

    rv.setAdapter(new SimpleStringAdapter(countries, vm::select));

Таким образом, фрагмент списка не знает о фрагменте деталей и передаёт данные только через ViewModel.

Во фрагменте DetailsFragment данные о странах хранятся во внутренней структуре Map<String, CountryInfo>. При изменении выбранной страны фрагмент подписывается на LiveData и обновляет текст с подробной информацией.

    vm.getSelected().observe(getViewLifecycleOwner(), countryName -> {
        CountryInfo info = db.get(countryName);
        if (info == null) return;
        tv.setText(
            "Страна: " + info.name + "\n" +
            "Столица: " + info.capital + "\n" +
            "Население: " + info.population
        );
    });

<img width="427" height="951" alt="manager1" src="https://github.com/user-attachments/assets/7565b297-9c66-40be-a6cf-013d88dc793b" />
<img width="432" height="951" alt="manager2" src="https://github.com/user-attachments/assets/fa5fb8af-20d3-44f4-9bb1-5ca035fb8c7e" />

resultapifragmentapp

В данном модуле было реализовано взаимодействие между фрагментами с использованием BottomSheetDialogFragment.
Основной экран (DataFragment) содержит поле ввода и кнопку. Во фрагменте DataFragment реализован ввод данных пользователем. Пользователь вводит текст в EditText и по нажатию кнопки этот текст передаётся другому фрагменту с помощью Fragment Result API.

    Bundle bundle = new Bundle();
    bundle.putString("key", text);
    getParentFragmentManager().setFragmentResult("requestKey", bundle);
После передачи данных открывается модальное окно в виде BottomSheet, реализованное через BottomSheetDialogFragment.

    BottomSheetFragment bottomSheet = new BottomSheetFragment();
    bottomSheet.show(getParentFragmentManager(), "ModalBottomSheet");
    
Фрагмент BottomSheetFragment подписывается на результат с тем же ключом (requestKey) и принимает данные без прямой связи с фрагментом-отправителем.

<img width="428" height="947" alt="result" src="https://github.com/user-attachments/assets/c221ebd0-1e04-4a14-8746-f2874c4fa223" />
