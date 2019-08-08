import React from 'react';
import {SelectField} from '../fields';
import {connect} from 'react-redux';
import {employeeActions} from '../_actions';
import {employeeService} from '../_services';

class EmployeeTable extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            employees: [],
            departments: [],

            pageCountOnBook: 2,
            recordsCountOnPage: 2,
            countOfBooks: 1,
            currentPageNumber: 1,
            currentBookNumber: 1,
            paginationData: [],
            pages: [],

            sortingField: "empID",

            pagesCountSelect: [{id: 2, name: 2}, {id: 3, name: 3}, {id: 5, name: 5}, {
                id: 10,
                name: 10
            }],
            recordsCountSelect: [{id: 2, name: 2}, {id: 3, name: 3}, {id: 5, name: 5}, {id: 10, name: 10},
                {id: 15, name: 15}, {id: 20, name: 20}, {id: 25, name: 25},
                {id: 30, name: 30}, {id: 40, name: 40}, {id: 50, name: 50}],

            findValue: null,
            notFoundMassage: "",
        }
    }


    componentWillMount() {
        this.getPageData();
    }

    getPageData() {
        const {
            currentPageNumber,
            recordsCountOnPage, pageCountOnBook,
            sortingField, findValue
        } = this.state;

        this.getEmployees(recordsCountOnPage, currentPageNumber, pageCountOnBook, sortingField, findValue);

    }

    getEmployees(recordsOnPage, currentPage, pageCountOnBook, sortingField, findValue) {

        employeeService.selectEmployeePage(recordsOnPage, currentPage, pageCountOnBook, sortingField, findValue)
            .then(data => {
                this.setState({
                    paginationData: data.outputPaginationData,
                    employees: data.pageWithData,
                });
                this.makePagesPagination(data.outputPaginationData);
            })

    }

    removeEmployee(id) {
        const {dispatch} = this.props;
        dispatch(employeeActions.removeEmployee(id));
        this.getPageData();
    }

    makePagesPagination(data) {
        const {paginationData} = this.state;
        let beginPage;
        let pagesCount;
        let pageCountInBook;
        let currentBook;

        currentBook = paginationData.currentBook;
        if (data != null) {
            beginPage = data.beginPage;
            pagesCount = data.countOfPages;
            pageCountInBook = data.pagesCountInBook;
            currentBook = data.currentBook;
        } else {
            beginPage = paginationData.beginPage;
            pagesCount = paginationData.countOfPages;
            pageCountInBook = paginationData.pagesCountInBook;
            currentBook = paginationData.currentBook;
        }

        var pages = [];

        for (var i = 0; i < pageCountInBook; i++) {
            let obj = {id: beginPage}
            if ((beginPage) > pagesCount) break;
            pages.push(obj);
            beginPage++;
        }

        this.setState({
            pages: pages
        })
    }

    changeBook(bookNumber) {

        let beginPage = 1
        let countOfBooks = this.state.paginationData.countOfBooks;
        let countOfPages = this.state.paginationData.countOfPages;
        let pagesInBook = this.state.paginationData.pagesCountInBook;

        if (bookNumber < 1) {
            bookNumber = countOfPages;
            if (countOfBooks > 1) beginPage = pagesInBook * (bookNumber) + 1;
        }

        if (bookNumber > 1) {
            if (bookNumber <= countOfBooks) beginPage = pagesInBook * (bookNumber - 1) + 1;
            if (bookNumber > countOfBooks) {
                bookNumber = 1;
                beginPage = 1;
            }
        }

        this.setState({
            currentBookNumber: bookNumber,
            currentPageNumber: beginPage
        })

        const {recordsCountOnPage, pageCountOnBook, sortingField, findValue} = this.state;
        this.getEmployees(recordsCountOnPage, beginPage, pageCountOnBook, sortingField, findValue);

    }

    changePage(pageNumber) {
        this.setState({
            currentPageNumber: pageNumber
        })
        const {recordsCountOnPage, pageCountOnBook, sortingField, findValue} = this.state;

        this.getEmployees(recordsCountOnPage, pageNumber, pageCountOnBook, sortingField, findValue);
    }

    onPagesCountInBookChange = event => {

        let pageCountOnBook = event.target.value;

        this.setState({
            pageCountOnBook: pageCountOnBook,
            currentBookNumber: 1,
            currentPageNumber: 1
        });

        const {recordsCountOnPage, sortingField, findValue} = this.state;
        this.getEmployees(recordsCountOnPage, 1, pageCountOnBook, sortingField, findValue)


    }

    onRecorsCountOnPageChange = event => {

        let recordsCountOnPage = event.target.value;

        this.setState({
            recordsCountOnPage: recordsCountOnPage,
            currentPageNumber: 1
        });

        const {pageCountOnBook, sortingField, findValue} = this.state;
        this.getEmployees(recordsCountOnPage, 1, pageCountOnBook, sortingField, findValue)
    }

    changeSorting(sortingField) {
        this.setState({
            sortingField: sortingField
        })
        const {recordsCountOnPage, currentPageNumber, pageCountOnBook, findValue} = this.state;
        this.getEmployees(recordsCountOnPage, currentPageNumber, pageCountOnBook, sortingField, findValue);
    }

    editForm = (employee) => {
        this.props.editForm(employee);
    }

    render() {

        const {

            employees,
            currentBookNumber, currentPageNumber, countOfBooks,
            recordsCountOnPage, pageCountOnBook, pagesCountSelect, recordsCountSelect, paginationData

        } = this.state;
        return (
            <div className="col-md-6 col-md-offset-3">
                <div>
                    <table border="1" className="table table-striped">
                        <thead className="thead-light">
                        <tr>
                            <th scope="col" onClick={(sortingField) => this.changeSorting("empId")}><b
                                className="btn btn-link">Id</b></th>

                            <th scope="col" onClick={(sortingField) => this.changeSorting("empName")}><b
                                className="btn btn-link">Name</b>
                            </th>
                            <th scope="col" onClick={(sortingField) => this.changeSorting("empActive")}>
                                <b className="btn btn-link">Active</b>
                            </th>
                            <th scope="col" onClick={(sortingField) => this.changeSorting("emp_dpID")}>
                                <b className="btn btn-link">Department</b>
                            </th>
                            <th colSpan="2">Operations</th>
                        </tr>
                        </thead>
                        <tbody>
                        {employees.map(employee =>
                            <tr scope="row">
                                <td>{employee.id}</td>
                                <td>{employee.name}</td>
                                <td>{employee.active}<input type="checkbox" checked={employee.active} /></td>
                                <td>{employee.department.name}</td>
                                <td>
                                    <button className="btn btn-danger"
                                            onClick={() => this.removeEmployee(employee.id)}>Remove
                                    </button>
                                </td>
                                <td>
                                    <button className="btn btn-success" onClick={() => this.editForm(employee)}>Edit
                                    </button>
                                </td>
                            </tr>
                        )}
                        </tbody>

                    </table>
                </div>

                <div>
                    <table>
                        <tbody>
                        <tr>
                            {currentBook => currentBookNumber}
                            <td><p className="btn btn-info"
                                   onClick={(currentBook) => this.changeBook(1)}>&lt;&lt;</p>
                            </td>
                            &nbsp;
                            <td><p className="btn btn-info"
                                   onClick={(currentBook) => this.changeBook(currentBookNumber - 1)}>&lt;</p>
                            </td>
                            &nbsp;
                            {this.state.pages.map((page) =>


                                <td><p className="btn btn-info"

                                       onClick={() => this.changePage(page.id)}
                                >
                                    {page.id === currentPageNumber &&
                                    <b className="btn btn-primary disabled"> {page.id} </b>}
                                    {page.id !== currentPageNumber && <u> {page.id} </u>}

                                </p></td>
                            )}

                            &nbsp;
                            {currentBook => currentBookNumber}
                            <td><p className="btn btn-info"
                                   onClick={(currentBook) => this.changeBook(currentBookNumber + 1)}>&gt;</p>
                            </td>
                            &nbsp;

                            {currentBook => countOfBooks}
                            <td><p className="btn btn-info"
                                   onClick={(currentBook) => this.changeBook(paginationData.countOfBooks)}>&gt;&gt;</p>
                            </td>

                            &nbsp;


                            <td><p className="btn btn-info"
                                   onClick={() => this.getPageData()}>Refresh</p>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </div>

                <div>
                    <table>
                        <tbody>
                        <tr>

                            <td>
                                <SelectField className="form-control"
                                             onChange={this.onPagesCountInBookChange}
                                             onBlur={this.onPagesCountInBookChange}

                                             selected={pageCountOnBook + 1}
                                             objects={pagesCountSelect}
                                             value={pageCountOnBook}
                                             label="Pages in book:&nbsp;"/>
                            </td>
                            <td>
                                <SelectField className="form-control"
                                             onChange={this.onRecorsCountOnPageChange}
                                             onBlur={this.onRecorsCountOnPageChange}

                                             selected={recordsCountOnPage + 1}
                                             objects={recordsCountSelect}
                                             value={recordsCountOnPage}
                                             label="Records on page:&nbsp;"/>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </div>


            </div>
        );
    }
}


const connectedEmployeeTable = connect()(EmployeeTable);
export {connectedEmployeeTable as EmployeeTable};